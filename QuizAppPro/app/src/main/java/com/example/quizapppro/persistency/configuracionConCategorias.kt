package com.example.quizapppro.persistency

import androidx.room.Embedded
import androidx.room.Relation

data class configuracionConCategorias (
        @Embedded val configuracion: ConfigEntity,
        @Relation(
                parentColumn = "idConfig",
                entityColumn = "idConfig"
        )
        val categorias: List<CategoryEntity>
)