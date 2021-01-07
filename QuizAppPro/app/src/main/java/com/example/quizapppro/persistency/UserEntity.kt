package com.example.quizapppro.persistency

import android.content.Context
import android.widget.Toast
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey(autoGenerate = true) val idUser: Int,
        @ColumnInfo(name = "userName")val userName:  String,
        @ColumnInfo(name = "password")val password: String
)