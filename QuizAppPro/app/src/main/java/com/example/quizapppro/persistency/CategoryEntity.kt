package com.example.quizapppro.persistency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tablaCategorias")
class CategoryEntity(
        @PrimaryKey(autoGenerate = true) val idCategoria : Int?,
        @ColumnInfo(name = "nombreCategoria") val nombreCategoria:  String,
        @ColumnInfo(name = "idConfig") val idConfig:Int
)
