package com.example.quizapppro.persistency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = arrayOf(
                UserEntity::class,
                ConfigEntity::class,
                CategoryEntity::class,
                JuegoEntity::class
        ),
        version = 1,
        exportSchema = false
)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun userDataDao():UserDao
    abstract fun juegoDataDao():JuegoDao
    abstract fun categoryDataDao():CategoryDao
    abstract fun configDataDao():ConfigDao





}