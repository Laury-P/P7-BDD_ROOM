package com.openclassrooms.arista.data.repository


import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.mapper.ExerciseMapper
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {

    // Get all exercises
    suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
            .first()
            .map { ExerciseMapper.fromDto(it) }
    }

    // Add a new exercise
    suspend fun addExercise (exercise: Exercise) {
        exerciseDao.insertExercise(ExerciseMapper.toDto(exercise))
    }

    // Delete an exercise
    suspend fun deleteExercise (exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in tje use case and view model
        exercise.id?.let {
            exerciseDao.deleteExerciseById(it)
        }
    }
}