package com.example.quizapppro.persistency

import androidx.room.*

@Dao
interface ConfigDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(config: ConfigEntity)

    @Query("DELETE FROM tablaConfiguraciones")
    fun deleteAll()

    @Query("SELECT * FROM tablaConfiguraciones")
    fun getConfiguraciones():List<ConfigEntity>

    @Query("SELECT dificultad FROM tablaConfiguraciones WHERE idConfig= :id")
    fun getDificultad(id:Int):String

    @Transaction
    @Query("SELECT * FROM tablaConfiguraciones")
    fun getConfiguacionYCategorias(): List<configuracionConCategorias>

    @Transaction
    @Query("SELECT * FROM tablaConfiguraciones WHERE idConfig= :configId")
    fun getCategoriesAndConfigByConfigId(configId:Int):configuracionConCategorias








}