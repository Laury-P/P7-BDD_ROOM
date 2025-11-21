package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.openclassrooms.arista.domain.model.ExerciseCategory

@Entity(
    tableName = "exercise",

    /**
     *
    foreignKeys = [
    ForeignKey(
    entity = UserDto::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE
    )
    ]
     */

)
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "start_time")
    var startTime: Long,

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "category")
    var category: ExerciseCategory,

    @ColumnInfo(name = "intensity")
    var intensity: Int,

    //@ColumnInfo(name = "user_id")
    //val userId: Long,
)