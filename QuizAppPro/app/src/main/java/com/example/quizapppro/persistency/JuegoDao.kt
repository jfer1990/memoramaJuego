package com.example.quizapppro.persistency

import androidx.room.*

@Dao
interface JuegoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(juego: JuegoEntity)

    @Query("DELETE FROM tablaJuego")
    fun deleteAll()

    @Query("SELECT * FROM tablaJuego")
    fun getJuegos():List<JuegoEntity>

    @Transaction
    @Query("SELECT * FROM tablaJuego")
    fun getJuegoYConfig(): List<JuegoYConfiguracion>



}