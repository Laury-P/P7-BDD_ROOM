package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.mapper.UserMapper
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first


class UserRepository(private val userDao: UserDtoDao) {

    // Get the current user
    suspend fun getUser(): User{
        return UserMapper.fromDto(userDao.getUser())
    }

    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(UserMapper.toDto(user))
    }

}