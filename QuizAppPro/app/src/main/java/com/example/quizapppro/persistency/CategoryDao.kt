package com.example.quizapppro.persistency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: CategoryEntity)

    @Query("DELETE FROM tablaCategorias")
    fun deleteAll()

    @Query("SELECT * FROM tablaCategorias")
    fun getCategories():List<CategoryEntity>

    @Query("SELECT * FROM tablaCategorias WHERE idConfig= :configId")
    fun getCategoriesByConfigId(configId:Int):List<CategoryEntity>

}