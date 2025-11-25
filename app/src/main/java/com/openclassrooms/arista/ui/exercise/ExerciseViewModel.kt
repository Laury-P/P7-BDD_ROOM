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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {

    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow.asStateFlow()

    init {
        loadAllExercises()
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = deleteExerciseUseCase.execute(exercise)

            result.onSuccess { loadAllExercises() }

            result.onFailure { e ->
                _errorFlow.value = e.message ?: "Erreur inconnu lors de la suppression."
            }
        }

    }

    private fun loadAllExercises() {
        viewModelScope.launch(Dispatchers.IO) {
            val exercises = getAllExercisesUseCase.execute()

            exercises.onSuccess { _exercisesFlow.value = it }

            exercises.onFailure { e ->
                _errorFlow.value = e.message ?: "Erreur inconnu lors du chargement."
            }
        }
    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = addNewExerciseUseCase.execute(exercise)

            result.onSuccess { loadAllExercises() }

            result.onFailure { e ->
                _errorFlow.value = e.message ?: "Erreur inconnu lors de l'ajout."
            }

        }
    }
}
