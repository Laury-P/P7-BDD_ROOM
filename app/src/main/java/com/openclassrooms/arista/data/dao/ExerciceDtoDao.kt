package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciceDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciceDtoDao {
    @Insert
    suspend fun insertExercise(exercise: ExerciceDto): Long

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciceDto>>

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExerciseById(id: Long)
}