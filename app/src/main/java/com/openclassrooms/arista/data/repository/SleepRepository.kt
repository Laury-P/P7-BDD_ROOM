package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.mapper.SleepMapper
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first


class SleepRepository(private val sleepDao: SleepDtoDao) {

    // Get all sleep records
    suspend fun allSleep(): List<Sleep>{
        return sleepDao.getAllSleep()
            .first()
            .map { SleepMapper.fromDto(it) }
    }
}