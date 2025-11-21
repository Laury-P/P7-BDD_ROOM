package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDtoDao {

    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<UserDto>>

}