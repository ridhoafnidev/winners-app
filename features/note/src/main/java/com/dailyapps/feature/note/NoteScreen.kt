package com.dailyapps.feature.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dailyapps.common.EmptyList
import com.dailyapps.common.components.TextFieldDropdown
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.components.ErrorUi
import com.dailyapps.common.components.LoadingUi
import com.dailyapps.common.components.tablayout.TabLayout
import com.dailyapps.common.components.tablayout.TabType
import com.dailyapps.feature.note.components.page.TabAbsentScreen
import com.dailyapps.feature.note.components.page.TabNoteScreen
import com.dailyapps.feature.note.state.AbsentScreenState
import com.dailyapps.feature.note.state.NoteScreenState

@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel
) {

    var filterAbsent by rememberSaveable { mutableStateOf("") }
    var filterNote by rememberSaveable { mutableStateOf("") }

    val menus = listOf(
        stringResource(R.string.absent),
        stringResource(R.string.note)
    )

    val absentResult by viewModel.absentScreenState.collectAsState()
    val noteResult by viewModel.noteScreenState.collectAsState()
    val currentTokenTypeId by viewModel.currentTokenTypeId.collectAsState()
    val studentId by viewModel.currentStudentId.collectAsState()
    val masterSchoolYear by viewModel.masterSchoolYear.collectAsState()

    val (token, tokenType) = currentTokenTypeId

    val currentSchoolYear by lazy {
        masterSchoolYear.first()
    }

    LaunchedEffect(currentTokenTypeId) {
        currentTokenTypeId.apply {
           if(token.isNotEmpty() && tokenType.isNotEmpty() && studentId != 0 && masterSchoolYear.isNotEmpty()) {
               val tahunAjaran = currentSchoolYear.id ?: 0
                val (_, _, semester, _, schoolYear) = currentSchoolYear

               filterAbsent = filterFormat(
                   schoolYear,
                   semester,
               )

               filterNote = filterFormat(
                   schoolYear,
                   semester,
               )

               viewModel.getAbsentById(
                   token = getToken(currentTokenTypeId),
                   siswaId = studentId,
                   tahunAjaranSemesterId = tahunAjaran
               )
               viewModel.getNoteById(
                   token = getToken(currentTokenTypeId),
                   siswaId = studentId,
                   tahunAjaranSemesterId = tahunAjaran
               )
           }
        }
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                title = stringResource(R.string.riwayat),
                onClickBack = { navController.popBackStack() },
                elevation = 1.dp
            ) {}
        }
    ) { contentPadding ->
        TabLayout(
            modifier = Modifier.padding(contentPadding),
            menus = menus,
            tabType = TabType.HISTORY,
            contentOne = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 8.dp),
                    text = filterAbsent,
                    label = "Semester & Tahun Ajaran",
                    itemsDropdown = masterSchoolYear.map { helper ->
                        filterFormat(
                            helper.tahunAjaran,
                            helper.semester,
                        )
                    },
                    onValueChange = { value ->
                        filterAbsent = value
                        val id = viewModel.spliteFiltered(masterSchoolYear, value)
                        viewModel.getAbsentById(
                            token = getToken(currentTokenTypeId),
                            siswaId = studentId,
                            tahunAjaranSemesterId = id
                        )
                    }
                )
                when (absentResult) {
                    is AbsentScreenState.Success -> {
                        (absentResult as AbsentScreenState.Success).absent.let { absent ->
                            absent.data?.let { absentItems ->
                                if (absentItems.isEmpty()) EmptyList()
                                else TabAbsentScreen(absentItems)
                            }
                        }
                    }
                    is AbsentScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is AbsentScreenState.Error -> {
                        (absentResult as AbsentScreenState.Error).message.let { msg ->
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    val id = viewModel.spliteFiltered(masterSchoolYear, filterAbsent)
                                    viewModel.getAbsentById(
                                        token = getToken(currentTokenTypeId),
                                        siswaId = studentId,
                                        tahunAjaranSemesterId = id
                                    )
                                })
                            }
                        }
                    }
                    else -> {}
                }
            },
            contentTwo = {
                TextFieldDropdown(
                    modifier = Modifier.padding(top = 8.dp),
                    text = filterNote,
                    label = "Semester & Tahun Ajaran",
                    itemsDropdown = masterSchoolYear.map { helper ->
                        filterFormat(
                            helper.tahunAjaran,
                            helper.semester,
                        )
                    },
                    onValueChange = { value ->
                        filterNote = value
                        val id = viewModel.spliteFiltered(masterSchoolYear, value)
                        viewModel.getNoteById(
                            token = getToken(currentTokenTypeId),
                            siswaId = studentId,
                            tahunAjaranSemesterId = id
                        )
                    }
                )
                when (noteResult) {
                    is NoteScreenState.Success -> {
                        (noteResult as NoteScreenState.Success).notes.let { notes ->
                            notes.let { noteItems ->
                                if (noteItems.isEmpty()) EmptyList()
                                else TabNoteScreen(noteItems)
                            }
                        }
                    }

                    is NoteScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LoadingUi(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    is NoteScreenState.Error -> {
                        (noteResult as NoteScreenState.Error).message.let { msg ->
                            Box(modifier = Modifier.fillMaxSize()) {
                                ErrorUi(message = msg, onButtonClick = {
                                    val id = viewModel.spliteFiltered(masterSchoolYear, filterNote)
                                    viewModel.getNoteById(
                                        token = getToken(currentTokenTypeId),
                                        siswaId = studentId,
                                        tahunAjaranSemesterId = id
                                    )
                                })
                            }
                        }
                    }
                    else -> {}
                }
            }
        )
    }
}

fun filterFormat(tahunAjaran: String?, semester: String?) =
    "Semester $semester - T.A $tahunAjaran"

fun getToken(currentToken: Pair<String, String>) =
    "${currentToken.second} ${currentToken.first}"