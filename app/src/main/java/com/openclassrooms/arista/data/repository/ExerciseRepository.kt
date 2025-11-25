package com.openclassrooms.arista.data.repository


import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.mapper.ExerciseMapper
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDtoDao) {

    // Get all exercises
    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
            .map { list -> list.map { ExerciseMapper.fromDto(it) } }
    }

    // Add a new exercise
    suspend fun addExercise (exercise: Exercise) {
        exerciseDao.insertExercise(ExerciseMapper.toDto(exercise))
    }

    // Delete an exercise
    suspend fun deleteExercise (exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in the use case and view model
        return exerciseDao.deleteExerciseById(exercise.id ?: 0)
    }
}
