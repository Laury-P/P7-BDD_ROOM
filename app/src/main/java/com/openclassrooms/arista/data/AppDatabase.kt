package com.openclassrooms.arista.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.arista.data.converter.ExerciseCategoryConverter
import com.openclassrooms.arista.data.converter.LocalDateTimeConverter
import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.entity.ExerciseDto
import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Database(
    entities = [UserDto::class, SleepDto::class, ExerciseDto::class],
    version = 1,
    exportSchema = false,)
@TypeConverters(
    LocalDateTimeConverter::class,
    ExerciseCategoryConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDtoDao
    abstract fun sleepDao(): SleepDtoDao
    abstract fun exerciseDao(): ExerciseDtoDao

    private class AppDatabaseCallback( private val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao(), database.sleepDao())
                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val DATABASE_NAME = "AristaDB"

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        suspend fun populateDatabase(userDao: UserDtoDao, sleepDao: SleepDtoDao) {
            sleepDao.insertSleep(
                SleepDto(
                    startTime = LocalDateTime.now().minusDays(1),
                    duration = 480,
                    quality = 4
                )
            )
            sleepDao.insertSleep(
                SleepDto(
                    startTime = LocalDateTime.now().minusDays(2),
                    duration = 450,
                    quality = 3
                )

            )
            userDao.insertUser(
                UserDto(
                    name = "John Doe",
                    email ="john.doe@exemple.com",
                )
            )
        }


    }

}

