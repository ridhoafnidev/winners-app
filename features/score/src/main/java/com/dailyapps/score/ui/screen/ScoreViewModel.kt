package com.dailyapps.score.ui.screen

import androidx.lifecycle.viewModelScope
import com.dailyapps.domain.usecase.MasterUseCase
import com.dailyapps.domain.usecase.ScoreUseCase
import com.dailyapps.domain.usecase.UserUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.ClassRoom
import com.dailyapps.entity.SchoolYear
import com.dailyapps.entity.Student
import com.dailyapps.score.ui.screen.detail.ScoreType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val scoreUseCase: ScoreUseCase,
    private val masterUseCase: MasterUseCase,
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private val _scoreScreenState = MutableStateFlow<ScoreScreenState>(ScoreScreenState.Loading)
    internal val scoreScreenState get() = _scoreScreenState

    private val _scoreTaskScreenState = MutableStateFlow<ScoreScreenState>(ScoreScreenState.Loading)
    internal val scoreTaskScreenState get() = _scoreTaskScreenState


    private val _scoreDailyTestScreenState =
        MutableStateFlow<ScoreScreenState>(ScoreScreenState.Loading)
    internal val scoreDailyTestScreenState get() = _scoreDailyTestScreenState


/*
    private val _scoreUasScreenState = MutableStateFlow<ScoreScreenState>(ScoreScreenState.Loading)
    internal val scoreUasScreenState get() = _scoreUasScreenState
*/


/*
    private val _scoreUtsScreenState = MutableStateFlow<ScoreScreenState>(ScoreScreenState.Loading)
    internal val scoreUtsScreenState get() = _scoreUtsScreenState
*/


    private val _masterSchoolYears = MutableStateFlow<List<SchoolYear>>(emptyList())
    internal val masterSchoolYear get() = _masterSchoolYears


    private val _masterClassRoom = MutableStateFlow<List<ClassRoom>>(emptyList())
    internal val masterClassRoom get() = _masterClassRoom


    private val _currentToken = MutableStateFlow("")
    private val currentToken get() = _currentToken


    private val _currentTokenType = MutableStateFlow("")
    private val currentTokenType get() = _currentTokenType

    private val _currentTokenTypeStudentId = MutableStateFlow(Pair("", ""))
    internal val currentTokenTypeStudentId get() = _currentTokenTypeStudentId


    private val _masterClassYears = MutableStateFlow<List<HelperClassYear>>(emptyList())
    internal val masterClassYears get() = _masterClassYears


    private val _currentClass = MutableStateFlow<Student?>(null)
    internal val currentClass get() = _currentClass

    init {
        getMasterScoolYear()
        getMasterClassRoom()
        getStudentClass()
        getLocal()
    }

    fun spliteScoreFiltered(
        masterClassYears: List<HelperClassYear>,
        keyword: String
    ): Pair<Int, Int> {
        val dataKeySplit = keyword.split("-")
        val classRoom = dataKeySplit.component1().trim().split(" ").component2()
        val semester = dataKeySplit.component2().trim().split(" ").component2()
        val year = dataKeySplit.component3().trim().split(" ").component2()

        val dataClassId =
            masterClassYears.first { classFilter -> classFilter.dataClass == classRoom }
        val dataYearId = masterClassYears.first { classFilter ->
            classFilter.dataYear == year && classFilter.dataSem == semester
        }

        Timber.d(">>> kelas: $classRoom, semester: $semester, ta: $year")
        Timber.d(">>> classId: ${dataClassId.classId}, yearId: $dataYearId.yearId")
        return Pair(dataClassId.classId, dataYearId.yearId)
    }

    fun getMasterClassYears(masterClassRoom: List<ClassRoom>, masterSchoolYear: List<SchoolYear>) {
        val data = arrayListOf<HelperClassYear>()
        masterClassRoom.forEach { room ->
            masterSchoolYear.forEach { year ->
                data.add(
                    HelperClassYear(
                        classId = room.id ?: 0,
                        dataClass = room.kelas ?: "",
                        yearId = year.id ?: 0,
                        dataYear = year.tahunAjaran ?: "",
                        dataSem = year.semester ?: ""
                    )
                )
            }
        }
        _masterClassYears.value = data
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getLocal() {
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
                    _currentTokenTypeStudentId.value = Pair(token, tokenType)
                }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.Eagerly, null)
            }
        }
    }

    private fun getMasterScoolYear() {
        viewModelScope.launch {
            masterUseCase.getMasterSchoolYears()
                .collect { schoolYear ->
                    _masterSchoolYears.value = schoolYear.data.sortedByDescending { it.tahunAjaran }
                }
        }
    }

    private fun getMasterClassRoom() {
        viewModelScope.launch {
            masterUseCase.getAllClassRooms()
                .collect {
                    _masterClassRoom.value = it.data
                }
        }
    }

    private fun getStudentClass() {
        viewModelScope.launch {
            _currentClass.value = masterUseCase.getStudentClass().data
        }
    }

    fun getScoreStudent(
        token: String,
        siswaId: Int,
        tahunAjaranId: Int,
        id: Int = 0,
        namaNilaiId: Int = 0,
        jenisNilaiId: Int = 0,
        kelasId: Int = 0,
        matpelId: Int = 0,
        isOrder: Int = 0,
        type: ScoreType
    ) {
        viewModelScope.launch {
            scoreUseCase.execute(
                ScoreUseCase.Params(
                    token = token,
                    id = id,
                    siswaId = siswaId,
                    tahunAjaranSemesterId = tahunAjaranId,
                    namaNilaiId = namaNilaiId,
                    jenisNilaiId = jenisNilaiId,
                    kelasId = kelasId,
                    matpelId = matpelId,
                    isOrder = isOrder
                )
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        when (type) {
                            ScoreType.OTHER -> _scoreScreenState.value = ScoreScreenState.Loading
                            ScoreType.TASK -> _scoreTaskScreenState.value = ScoreScreenState.Loading
                            ScoreType.DAILY_TEST -> _scoreDailyTestScreenState.value =
                                ScoreScreenState.Loading

                            /*ScoreType.UAS -> _scoreUasScreenState.value = ScoreScreenState.Loading
                            ScoreType.UTS -> _scoreUtsScreenState.value = ScoreScreenState.Loading*/
                        }

                    }

                    is Resource.Success -> {
                        when (type) {
                            ScoreType.OTHER -> _scoreScreenState.value =
                                ScoreScreenState.Success(result.data)

                            ScoreType.TASK -> _scoreTaskScreenState.value =
                                ScoreScreenState.Success(result.data)

                            ScoreType.DAILY_TEST -> _scoreDailyTestScreenState.value =
                                ScoreScreenState.Success(result.data)

                           /* ScoreType.UAS -> _scoreUasScreenState.value =
                                ScoreScreenState.Success(result.data)

                            ScoreType.UTS -> _scoreUtsScreenState.value =
                                ScoreScreenState.Success(result.data)*/
                        }
                    }

                    is Resource.Error -> {
                        when (type) {
                            ScoreType.OTHER -> _scoreScreenState.value =
                                ScoreScreenState.Error(result.msg)

                            ScoreType.TASK -> _scoreTaskScreenState.value =
                                ScoreScreenState.Error(result.msg)

                            ScoreType.DAILY_TEST -> _scoreDailyTestScreenState.value =
                                ScoreScreenState.Error(result.msg)

                            /*ScoreType.UAS -> _scoreUasScreenState.value =
                                ScoreScreenState.Error(result.msg)

                            ScoreType.UTS -> _scoreUtsScreenState.value =
                                ScoreScreenState.Error(result.msg)*/
                        }
                    }
                }
            }
        }
    }

}

data class HelperClassYear(
    val classId: Int,
    val dataClass: String,
    val yearId: Int,
    val dataYear: String,
    val dataSem: String
)