package com.example.quizapppro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.not
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapppro.Modelo.Usuario
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapppro.Modelo.Juego
import com.example.quizapppro.persistency.UserEntity
import com.example.quizapppro.persistency.UserRoomDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var nombreUsuario: EditText
    private lateinit var passwordUsuario: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnRegistrar: Button
    private lateinit var btnBorrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db =  Room.databaseBuilder(
                applicationContext,
                UserRoomDatabase::class.java,
                "database4.db").allowMainThreadQueries().build()


        nombreUsuario = findViewById(R.id.user_editText)
        passwordUsuario = findViewById(R.id.password_editText)
        btnIniciarSesion = findViewById(R.id.login_button)
        btnRegistrar = findViewById(R.id.registar_button)
        btnBorrar = findViewById(R.id.borrarBD_button)

        btnIniciarSesion.setOnClickListener {
            var usuario = nombreUsuario.text.toString()
            var password = passwordUsuario.text.toString()
            var listaUsuarios: List<UserEntity> = db.userDataDao().getUsers()


            if((usuario.length == 0) or (password.length == 0))
            {
                Toast.makeText(this, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            }
            else
            {

                if (Usuario.esUsuarioValido(listaUsuarios, usuario, password)) {
                    Toast.makeText(this, "Bienvenido a QuizAppPro", Toast.LENGTH_SHORT).show()
                    //var juego = Juego()
                    //var user = Usuario(usuario, juego)
                    db.close()
                    val intent = Intent(this, MenuActivity::class.java).apply {
                        action = Intent.ACTION_SEND
                        putExtra("usuario", usuario)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
                }

            }


        }

        btnRegistrar.setOnClickListener{
            var usuario = nombreUsuario.text.toString()
            var password = passwordUsuario.text.toString()
            var listaUsuarios = db.userDataDao().getUsers()

            if((usuario.length == 0) or (password.length == 0))
            {
                Toast.makeText(this, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            }
            else
            {


                if (!Usuario.esPerfilRegistrado(listaUsuarios,usuario)) {
                    var nuevoId = listaUsuarios.size+1
                    db.userDataDao().insert(UserEntity(nuevoId,usuario, password))
                    Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Este usuario ya existe. Elija otro nombre de usuario", Toast.LENGTH_SHORT).show()
                }
            }

        }

        btnBorrar.setOnClickListener {
            db.userDataDao().deleteAll()
            Toast.makeText(this, "Base de datos borrada", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart(){
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}