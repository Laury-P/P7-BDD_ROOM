package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.entity.UserDto
import com.openclassrooms.arista.domain.model.User

object UserMapper {

    fun fromDto(dto: UserDto): User {
        return User(
            name = dto.name,
            email = dto.email,
        )
    }

    fun toDto(user: User): UserDto {
        return UserDto(
            name = user.name,
            email = user.email,
        )
    }

}