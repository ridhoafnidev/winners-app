package com.dailyapps.feature.absent

import androidx.lifecycle.viewModelScope
import com.dailyapps.domain.usecase.AbsentUseCase
import com.dailyapps.domain.usecase.CreateAbsentUseCase
import com.dailyapps.domain.usecase.MasterUseCase
import com.dailyapps.domain.usecase.UserUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.SchoolYear
import com.dailyapps.entity.Student
import com.dailyapps.feature.absent.components.DateUtil
import com.dailyapps.feature.absent.state.AbsentScreenState
import com.dailyapps.feature.absent.state.CreateAbsentScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AbsentViewModel @Inject constructor(
    private val createAbsentUseCase: CreateAbsentUseCase,
    private val userUseCase: UserUseCase,
    private val masterUseCase: MasterUseCase,
    private val absentUseCase: AbsentUseCase,
) : BaseViewModel() {

    private val _createAbsentScreenState =
        MutableStateFlow<CreateAbsentScreenState>(CreateAbsentScreenState.Empty)
    val createAbsentScreenState: StateFlow<CreateAbsentScreenState> get() = _createAbsentScreenState

    private val _currentLatitude = MutableStateFlow(0.0)
    internal val currentLatitude get() = _currentLatitude

    private val _currentLongitude = MutableStateFlow(0.0)
    internal val currentLongitude get() = _currentLongitude

    private val _currentAddress = MutableStateFlow("")
    internal val currentAddress get() = _currentAddress

    private val _currentChooseAbsent = MutableStateFlow("Hadir")
    internal val currentChooseAbsent get() = _currentChooseAbsent

    private val _currentToken = MutableStateFlow("")
    internal val currentToken get() = _currentToken

    private val _currentTokenType = MutableStateFlow("")
    internal val currentTokenType get() = _currentTokenType

    private val _currentStudentId = MutableStateFlow(0)
    internal val currentStudentId get() = _currentStudentId

    private val _currentTokenTypeId = MutableStateFlow(Pair("", ""))
    internal val currentTokenTypeId get() = _currentTokenTypeId

    private val _masterSchoolYears = MutableStateFlow<List<SchoolYear>>(emptyList())
    internal val masterSchoolYear get() = _masterSchoolYears

    private val _absentScreenState = MutableStateFlow<AbsentScreenState>(AbsentScreenState.Loading)
    internal val absentScreenState get() = _absentScreenState

    internal val isMock get() = _isMock

    private val _isMock = MutableStateFlow(false)

    private val _currentClass = MutableStateFlow<Student?>(null)
    internal val currentClass get() = _currentClass


    init {
        getMasterScoolYear()
        getStudentClass()
        getLocal()
    }

    private fun getStudentClass() {
        viewModelScope.launch {
            //_currentClass.value = masterUseCase.getStudentClass().data
        }
    }

    fun setIsMock(isMock: Boolean) {
        _isMock.value = isMock
    }

    fun setLatitude(latitude: Double) {
        _currentLatitude.value = latitude
    }

    fun setLongitude(longitude: Double) {
        _currentLongitude.value = longitude
    }

    fun setCurrentAddress(address: String) {
        _currentAddress.value = address
    }

    fun setCurrentChooseAbsent(chooseAbsent: String) {
        _currentChooseAbsent.value = chooseAbsent
    }

    fun createAbsent(
        token: String,
        siswaId: Int,
        tahunAjaranSemesterId: Int,
        jenisAbsensiId: Int
    ) {
        viewModelScope.launch {
            createAbsentUseCase.execute(
                CreateAbsentUseCase.Params(
                    token = token,
                    siswaId = siswaId,
                    jamMasuk = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()),
                    tahunAjaranSemesterId = tahunAjaranSemesterId,
                    tanggalAbsensi = DateUtil.getCurrentDate(),
                    jenisAbsensiId = jenisAbsensiId,
                    latitude = _currentLatitude.value.toString(),
                    longitude = _currentLongitude.value.toString(),
                    foto = "foto"
                )
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _createAbsentScreenState.value =
                        CreateAbsentScreenState.Loading

                    is Resource.Success -> _createAbsentScreenState.value =
                        CreateAbsentScreenState.Success(result.data)

                    is Resource.Error -> _createAbsentScreenState.value =
                        CreateAbsentScreenState.Error(result.msg)
                }
            }
        }
    }

    fun getAbsentById(siswaId: Int, token: String, tahunAjaranSemesterId: Int) {
        viewModelScope.launch {
            absentUseCase.execute(AbsentUseCase.Params(siswaId, token, tahunAjaranSemesterId))
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> _absentScreenState.value = AbsentScreenState.Loading
                        is Resource.Success -> _absentScreenState.value =
                            AbsentScreenState.Success(result.data)

                        is Resource.Error -> _absentScreenState.value =
                            AbsentScreenState.Error(result.msg)
                    }
                }
        }
    }

    fun getMasterScoolYear() {
        viewModelScope.launch {
            masterUseCase.getMasterSchoolYears().collect {
                _masterSchoolYears.value = it.data.sortedByDescending { it.tahunAjaran }
            }
        }
    }

    fun getLocal() {
        viewModelScope.launch {
            userUseCase.getCurrentToken().onEach { token ->
                _currentToken.value = token
            }.flatMapConcat {
                userUseCase.getCurrentTokenType().onEach { tokenType ->
                    _currentTokenType.value = tokenType
                }
            }.collect {
                combine(
                    currentToken,
                    currentTokenType
                ) { token, tokenType ->
                    _currentTokenTypeId.value = Pair(token, tokenType)
                }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.Eagerly, null)
            }
        }
    }

}