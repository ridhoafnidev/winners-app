package com.dailyapps.feature.note

import androidx.lifecycle.viewModelScope
import com.dailyapps.domain.usecase.AbsentUseCase
import com.dailyapps.domain.usecase.MasterUseCase
import com.dailyapps.domain.usecase.NoteUseCase
import com.dailyapps.domain.usecase.UserUseCase
import com.dailyapps.domain.utils.Resource
import com.dailyapps.entity.SchoolYear
import com.dailyapps.feature.note.state.AbsentScreenState
import com.dailyapps.feature.note.state.NoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class NoteViewModel @Inject constructor(
    private val absentUseCase: AbsentUseCase,
    private val userUseCase: UserUseCase,
    private val noteUseCase: NoteUseCase,
    private val masterUseCase: MasterUseCase
): BaseViewModel() {

    private val _absentScreenState = MutableStateFlow<AbsentScreenState>(AbsentScreenState.Loading)
    internal val absentScreenState get() = _absentScreenState


    private val _currentToken = MutableStateFlow("")
    private val currentToken get() = _currentToken


    private val _currentTokenType = MutableStateFlow("")
    private val currentTokenType get() = _currentTokenType


    private val _currentStudentId = MutableStateFlow(0)
    val currentStudentId get() = _currentStudentId


    private val _currentTokenTypeId = MutableStateFlow(Pair("", ""))
    internal val currentTokenTypeId get() = _currentTokenTypeId


    private val _currentStudent = MutableStateFlow(Pair("", ""))
    internal val currentStudent get() = _currentStudent

    private val _noteScreenState = MutableStateFlow<NoteScreenState>(NoteScreenState.Loading)
    internal val noteScreenState get() = _noteScreenState


    private val _masterSchoolYears = MutableStateFlow<List<SchoolYear>>(emptyList())
    internal val masterSchoolYear get() = _masterSchoolYears


    init {
        getMasterScoolYear()
        getMasterCurrentStudent()
        getLocal()
    }

    private fun getMasterCurrentStudent() {
        viewModelScope.launch {
            //_currentStudentId.value = masterUseCase.getStudentClass().data.id ?: 0
        }

    }

    private fun getMasterScoolYear() {
        viewModelScope.launch {
            masterUseCase.getMasterSchoolYears().collect { schoolYear ->
                _masterSchoolYears.value = schoolYear.data.sortedByDescending { it.tahunAjaran }
            }
        }
    }

    fun getAbsentById(siswaId: Int, token: String, tahunAjaranSemesterId: Int) {
        viewModelScope.launch {
            absentUseCase.execute(AbsentUseCase.Params(siswaId, token, tahunAjaranSemesterId)).collect { result ->
                when(result) {
                    is Resource.Loading ->  _absentScreenState.value = AbsentScreenState.Loading
                    is Resource.Success -> _absentScreenState.value = AbsentScreenState.Success(result.data)
                    is Resource.Error -> _absentScreenState.value = AbsentScreenState.Error(result.msg)
                }
            }
        }
    }

    fun spliteFiltered(masterSchoolYear: List<SchoolYear>, keyword: String): Int {
        val dataKeySplit = keyword.split("-")
        val semester = dataKeySplit.component1().trim().split(" ").component2()
        val year = dataKeySplit.component2().trim().split(" ").component2()

        val schoolYear = masterSchoolYear.first { filter ->
            filter.tahunAjaran == year && filter.semester == semester
        }

        Timber.d(">> TA: $year, semester: $semester")
        Timber.d(">> id: ${schoolYear.id}")
        return schoolYear.id ?: 0
    }

    fun getNoteById(siswaId: Int, token: String, tahunAjaranSemesterId: Int) {
        viewModelScope.launch {
            noteUseCase.execute(NoteUseCase.Params(siswaId, token, tahunAjaranSemesterId)).collect { result ->
                when(result) {
                    is Resource.Loading -> _noteScreenState.value = NoteScreenState.Loading
                    is Resource.Success -> _noteScreenState.value = NoteScreenState.Success(result.data)
                    is Resource.Error -> _noteScreenState.value = NoteScreenState.Error(result.msg)
                }
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
                combine(currentToken, currentTokenType) { token, tokenType ->
                    _currentTokenTypeId.value = Pair(token, tokenType)
                }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.Eagerly, null)
            }
        }
    }

}