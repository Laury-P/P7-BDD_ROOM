package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.mapper.SleepMapper
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first


class SleepRepository(private val sleepDao: SleepDtoDao) {

    // Get all sleep records
    suspend fun allSleeps(): Result<List<Sleep>> {
        return try {
            val sleeps = sleepDao.getAllSleep()
                .first()
                .map { SleepMapper.fromDto(it) }
            Result.success(sleeps)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}