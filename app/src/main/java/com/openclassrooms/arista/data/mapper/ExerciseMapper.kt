package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.entity.ExerciseDto
import com.openclassrooms.arista.domain.model.Exercise

object ExerciseMapper {

    fun fromDto(dto: ExerciseDto): Exercise{
        return Exercise(
            id = dto.id,
            startTime = dto.startTime,
            duration = dto.duration,
            category = dto.category,
            intensity = dto.intensity
        )
    }

    fun toDto(exercise: Exercise): ExerciseDto{
        return ExerciseDto(
            id = exercise.id ?: 0,
            startTime = exercise.startTime,
            duration = exercise.duration,
            category = exercise.category,
            intensity = exercise.intensity
        )
    }
}