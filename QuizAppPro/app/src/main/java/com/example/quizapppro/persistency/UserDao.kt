package com.example.quizapppro.persistency

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users")
    fun getUsers():List<UserEntity>

    @Query("SELECT idUser FROM users WHERE userName= :usuario")
    fun getUserIdByUsername(usuario:String):List<Int>

    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithJuegos(): List<UsuarioConJuegos>


}
