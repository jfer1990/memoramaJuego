package com.example.quizapppro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.example.quizapppro.Modelo.Usuario
import com.example.quizapppro.persistency.UserEntity
import com.example.quizapppro.persistency.UserRoomDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


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

        var db =  Room.databaseBuilder(
                applicationContext,
                UserRoomDatabase::class.java,
                "database4.db").allowMainThreadQueries().build()
        val db2 = FirebaseFirestore.getInstance()


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
            var listaUsuarios = db2.collection("users").get().addOnCompleteListener(
                OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            })

            if((usuario.length == 0) or (password.length == 0))
            {
                Toast.makeText(this, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            }
            else
            {


                if (!Usuario.esPerfilRegistrado(listaUsuarios,usuario)) {

                    // Create a new user with a first and last name

                    // Create a new user with a first and last name
                    val user: MutableMap<String, Any> = HashMap()
                    user["username"] = usuario
                    user["password"] = password


                    // Add a new document with a generated ID

                    // Add a new document with a generated ID
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                            Log.d(TAG,
                                "DocumentSnapshot added with ID: " + documentReference.id
                            )
                        })
                        .addOnFailureListener(OnFailureListener { e ->
                            Log.w(TAG,
                                "Error adding document",
                                e
                            )
                        })
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