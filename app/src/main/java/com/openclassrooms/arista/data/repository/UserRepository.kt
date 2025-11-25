package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.mapper.UserMapper
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class UserRepository @Inject constructor(private val userDao: UserDtoDao) {

    // Get the current user
    suspend fun getUser(): Result<User>{
        return try{
            val result = UserMapper.fromDto(userDao.getUser())
            Result.success(result)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun insertUser(user: User): Result<Long> {
        return try{
            val result = userDao.insertUser(UserMapper.toDto(user))
            Result.success(result)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

}
