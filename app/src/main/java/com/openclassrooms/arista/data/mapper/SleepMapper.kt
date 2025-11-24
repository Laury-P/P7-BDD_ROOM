package com.openclassrooms.arista.data.mapper

import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.domain.model.Sleep

object SleepMapper {

    fun fromDto(dto: SleepDto): Sleep {
        return Sleep(
            startTime = dto.startTime,
            duration = dto.duration,
            quality = dto.quality
        )
    }

    fun toDto(sleep: Sleep): SleepDto{
        return SleepDto(
            startTime = sleep.startTime,
            duration = sleep.duration,
            quality = sleep.quality,
        )
    }
}