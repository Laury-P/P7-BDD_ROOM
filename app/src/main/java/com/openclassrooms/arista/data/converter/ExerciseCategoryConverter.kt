package com.openclassrooms.arista.data.converter

import androidx.room.TypeConverter
import com.openclassrooms.arista.domain.model.ExerciseCategory

class ExerciseCategoryConverter {

    @TypeConverter
    fun fromCategory(category: ExerciseCategory): String{
        return category.name
    }

    @TypeConverter
    fun toCategory(value: String): ExerciseCategory {
        return ExerciseCategory.valueOf(value)
    }
}