package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.openclassrooms.arista.data.entity.SleepDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDtoDao {

    @Query("SELECT * FROM sleep")
    fun getAllSleep(): Flow<List<SleepDto>>

}