package com.example.quizapppro.persistency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tablaConfiguraciones")
class ConfigEntity (
        @PrimaryKey(autoGenerate = true) val idConfig: Int,

        @ColumnInfo(name = "dificultad") val dificultad:  String,

        @ColumnInfo(name = "cantidadPreguntas")val cantidadPreguntas: Int,

        @ColumnInfo(name= "statusPistas") val statusPistas: Boolean,

        val idJuego:Int
)
