package com.example.quizapppro



import android.content.Intent

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.widget.Button

import android.widget.TextView

import android.widget.Toast

import androidx.room.Room

import com.example.quizapppro.persistency.JuegoEntity

import com.example.quizapppro.persistency.UserRoomDatabase



class MenuActivity : AppCompatActivity() {

    private lateinit var txtUsuario: TextView

    private lateinit var btnChangeUser: Button

    private lateinit var btnEliminarPerfil: Button

    private lateinit var btnJugar:Button

    private lateinit var btnOpciones:Button



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)



        txtUsuario = findViewById(R.id.user_textView)

        btnChangeUser = findViewById(R.id.changeUser_button)

        btnEliminarPerfil = findViewById(R.id.deleteUser_button)

        btnJugar = findViewById(R.id.play_button)

        btnOpciones = findViewById(R.id.options_button)



        var user :String = intent.getStringExtra("usuario") as String

        txtUsuario.setText("$user")



        btnChangeUser.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }



        btnEliminarPerfil.setOnClickListener {

            TODO("Not implemented yet")

        }



        btnJugar.setOnClickListener {



            val intent = Intent(this, GameActivity::class.java).apply {

                action=Intent.ACTION_SEND

                putExtra("usuario",user)

                putExtra("configuracion",false)

            }

            startActivity(intent)

        }



        btnOpciones.setOnClickListener{

            val intent = Intent(this, OptionsActivity::class.java)

            startActivity(intent)

        }









    }

}
