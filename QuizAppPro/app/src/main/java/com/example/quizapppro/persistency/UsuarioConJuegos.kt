package com.example.quizapppro.persistency

import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioConJuegos(
        @Embedded val user: UserEntity,
        @Relation(
                parentColumn = "idUser",
                entityColumn = "idUser"
        )
        val juegos: List<JuegoEntity>
)