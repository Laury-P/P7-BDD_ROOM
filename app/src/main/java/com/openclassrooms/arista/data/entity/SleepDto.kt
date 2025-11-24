package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "sleep",
    /**
     *
     foreignKeys = [
             ForeignKey(
                 entity = UserDto::class,
                 parentColumns = ["id"],
                 childColumns = ["user_id"],
                onDelete = ForeignKey.CASCADE,
             )
     ]
     */

)
data class SleepDto(

    //@ColumnInfo(name = "id")
    //val id: Long = 0,

    @PrimaryKey
    @ColumnInfo(name = "start_time")
    var startTime: LocalDateTime,

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "quality")
    var quality: Int,

    //@ColumnInfo(name = "user_id")
    //val userId: Long,
)
