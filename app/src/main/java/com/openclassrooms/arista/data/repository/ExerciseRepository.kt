package com.openclassrooms.arista.data.repository


import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.mapper.ExerciseMapper
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {

    // Get all exercises
    suspend fun getAllExercises(): Result<List<Exercise>> {
        return try {
            val exercises = exerciseDao.getAllExercises()
                .first()
                .map { ExerciseMapper.fromDto(it) }
            Result.success(exercises)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Add a new exercise
    suspend fun addExercise (exercise: Exercise): Result<Boolean> {
        return try {
            exerciseDao.insertExercise(ExerciseMapper.toDto(exercise))
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Delete an exercise
    suspend fun deleteExercise (exercise: Exercise): Result<Boolean> {
        // If there is no id, you can raise an exception and catch it in the use case and view model
        return try {
            exercise.id?.let {
                exerciseDao.deleteExerciseById(it)
                return Result.success(true)
            }
            Result.failure(IllegalArgumentException("Exercise id is null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}