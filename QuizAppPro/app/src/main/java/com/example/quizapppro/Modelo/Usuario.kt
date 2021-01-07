package com.example.quizapppro.Modelo


import com.example.quizapppro.persistency.UserDao
import com.example.quizapppro.persistency.UserEntity
import java.util.*
import kotlin.collections.ArrayList


class Usuario(userName:String, juegoCurso : Juego): UserInterface {

    private var userName: String = userName
    private var juegoEnCurso: Juego = juegoCurso


    companion object validadorUsuario{


        //HISTORIAL DE PARTIDAS:
        fun mostrarPuntuacionesOrdenas(): ArrayList<Int>
        {
            TODO("Not yet implemented")
        }
        fun esPerfilRegistrado(usersRegistered:List<UserEntity> ,userName: String): Boolean{
            usersRegistered.forEach {
                var currentUserName = it.userName
                if (currentUserName.equals(userName))
                    return true
            }
            return false
        }
        fun esUsuarioValido(usersRegistered:List<UserEntity> ,userName: String, password: String):Boolean{
            usersRegistered.forEach {
                var currentUserName = it.userName
                var currentPassword = it.password
                if (currentUserName.equals(userName) and currentPassword.equals(password))
                    return true
            }
            return false
        }

    }

    //FUNCIONES:

    fun registrarPartida(): Boolean
    {
        return false
    }

    override fun userNameRepetido(userName: String) {
        TODO("Not yet implemented")
    }

    override fun encriptarPassword(password: String): String {
        TODO("Not yet implemented")
    }

}
