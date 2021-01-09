package com.example.quizapppro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.quizapppro.Modelo.Usuario
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var nombreUsuario: EditText
    private lateinit var passwordUsuario: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnRegistrar: Button
    private lateinit var btnBorrar: Button

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = FirebaseFirestore.getInstance()


        nombreUsuario = findViewById(R.id.user_editText)
        passwordUsuario = findViewById(R.id.password_editText)
        btnIniciarSesion = findViewById(R.id.login_button)
        btnRegistrar = findViewById(R.id.registar_button)
        btnBorrar = findViewById(R.id.borrarBD_button)

        btnIniciarSesion.setOnClickListener {
            var usuario = nombreUsuario.text.toString()
            var password = passwordUsuario.text.toString()
            //var listaUsuarios: List<UserEntity> = db.userDataDao().getUsers()


            if((usuario.length == 0) or (password.length == 0))
            {
                Toast.makeText(this, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            }
            else
            {



                val docRef = db.collection("users").document(usuario)
                docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                                var pepito = document.data;
                                var pas = pepito?.get("password")
                                if (pas != null) {
                                    if (pas.equals(password)){
                                        val intent = Intent(this, MenuActivity::class.java).apply {
                                            action = Intent.ACTION_SEND
                                            putExtra("usuario", usuario)
                                        }
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            } else {
                                Log.d(TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "get failed with ", exception)
                        }




                /*if (Usuario.esUsuarioValido(listaUsuarios, usuario, password)) {
                    Toast.makeText(this, "Bienvenido a QuizAppPro", Toast.LENGTH_SHORT).show()
                    //var juego = Juego()
                    //var user = Usuario(usuario, juego)
                    val intent = Intent(this, MenuActivity::class.java).apply {
                        action = Intent.ACTION_SEND
                        putExtra("usuario", usuario)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
                }

                 */

            }


        }

        btnRegistrar.setOnClickListener{
            var usuario = nombreUsuario.text.toString()
            var password = passwordUsuario.text.toString()


            var users = db.collection("users");


            val usuarioNuevo = hashMapOf("username" to usuario, "password" to password)
            users.document(usuario).set(usuarioNuevo)

            Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show()


            if((usuario.length == 0) or (password.length == 0))
            {
                Toast.makeText(this, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            }
            else
            {


                if (true) {

                    // Create a new user with a first and last name




                    // Add a new document with a generated ID

                    // Add a new document with a generated ID

                }
                else{
                    Toast.makeText(this, "Este usuario ya existe. Elija otro nombre de usuario", Toast.LENGTH_SHORT).show()
                }
            }

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