package com.example.quizapppro.persistency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tablaJuego")
data class JuegoEntity(
        @PrimaryKey(autoGenerate = true)val idJuego: Int,

        @ColumnInfo(name = "score")val score:  Int,

        @ColumnInfo(name = "fecha")val fecha: String,

        val idUser : Int
)
