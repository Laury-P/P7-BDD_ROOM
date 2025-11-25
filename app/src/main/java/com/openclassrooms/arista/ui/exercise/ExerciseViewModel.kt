package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {
    val exercisesFlow: StateFlow<List<Exercise>> = getAllExercisesUseCase.execute()
        // convert flow into state flow
        .stateIn(
            // scope in which we should execte the coroutine
            scope = viewModelScope,
            // Define when we start and stop the sharing the flow
            started = SharingStarted.WhileSubscribed(5_000),
            // need an initial value until the flow emit its first value
            initialValue = emptyList()
    )

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow.asStateFlow()

    // NOT NEEDED ANYMORE BECAUSE WE WILL EMITE VALUES DIRECTLY FROM exercisesFlow
//    init {
//        loadAllExercises()
//    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            deleteExerciseUseCase.execute(exercise)
        }

    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            addNewExerciseUseCase.execute(exercise)

        // NOT NEEDED ANYMORE BECAUSE WE WILL EMITE VALUES DIRECTLY FROM exercisesFlow as soon as they are available
            //result.onSuccess { loadAllExercises() }

        }
    }
}
