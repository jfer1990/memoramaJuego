package com.example.quizapppro.persistency

import androidx.room.Embedded
import androidx.room.Relation

data class JuegoYConfiguracion (
        @Embedded val juego: JuegoEntity,
        @Relation(
                parentColumn = "idJuego",
                entityColumn = "idJuego"
        )
        val configuracion: ConfigEntity
)