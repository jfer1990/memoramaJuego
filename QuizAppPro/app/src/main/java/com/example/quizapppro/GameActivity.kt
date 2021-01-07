package com.example.quizapppro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.example.quizapppro.Modelo.Categoria
import com.example.quizapppro.Modelo.Opciones
import com.example.quizapppro.persistency.*

class GameActivity : AppCompatActivity() {
    private lateinit var txvPorcentaje:TextView
    private lateinit var txvCurrentQuestion:TextView
    private lateinit var btnOp1:Button
    private lateinit var btnOp2:Button
    private lateinit var btnOp3:Button
    private lateinit var btnOp4:Button

    var nuevoIdConfig = -1
    var nuevoJuegoId = -1
    var score = -1
    var fecha = ""
    var numPreguntas = 5
    var dificultad = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnOp1 = findViewById(R.id.op1_button)
        btnOp2 = findViewById(R.id.op2_button)
        btnOp3 = findViewById(R.id.op3_button)
        btnOp4 = findViewById(R.id.op4_button)
        txvCurrentQuestion = findViewById(R.id.currentQuestion_textView)

        var db =  Room.databaseBuilder(
                applicationContext,
                UserRoomDatabase::class.java,
                "database4.db").allowMainThreadQueries().build()


        var tieneConfigPreCargadas :Boolean = intent.getBooleanExtra("configuracion",false)
        if(tieneConfigPreCargadas){
            System.out.println("holi")
        }
        else{
            var user :String = intent.getStringExtra("usuario") as String
            var idUser:Int = getUserId(user, db.userDataDao().getUsers())
            score = 0
            fecha = "14Diciembre"
            numPreguntas = 5
            dificultad = "baja"
            var listaJuegos = db.juegoDataDao().getJuegos()
            nuevoJuegoId = listaJuegos.size+1
            db.juegoDataDao().insert(JuegoEntity(nuevoJuegoId,score,fecha,idUser))
            /*
            * Categorias
            * geografia, culturageneral, deportes, arteycine, tecnologia, catafixia
            * */
            var listaConfiguraciones = db.configDataDao().getConfiguraciones()
            nuevoIdConfig = listaConfiguraciones.size+1
            db.configDataDao().insert(ConfigEntity(nuevoIdConfig,dificultad,numPreguntas,false,nuevoJuegoId))

            db.categoryDataDao().insert(CategoryEntity(null,"geografia",nuevoIdConfig))
            db.categoryDataDao().insert(CategoryEntity(null,"cultura",nuevoIdConfig))
            db.categoryDataDao().insert(CategoryEntity(null,"deportes",nuevoIdConfig))
            db.categoryDataDao().insert(CategoryEntity(null,"arte",nuevoIdConfig))
            db.categoryDataDao().insert(CategoryEntity(null,"tecnologia",nuevoIdConfig))
            db.categoryDataDao().insert(CategoryEntity(null,"catafixia",nuevoIdConfig))
            db.close()

        }
        cargarVista()



    }
    fun getUserId(username:String, usuarios:List<UserEntity>):Int{
        usuarios.forEach {
            if(it.userName.equals(username))
                return it.idUser
        }
        return -1
    }
    fun cargarCategorias():ArrayList<Categoria>{
        TODO()
    }
    fun crearNuevaConfigurcionOpciones():Categoria{TODO()}
    fun cargarVista(){
        var db =  Room.databaseBuilder(
                applicationContext,
                UserRoomDatabase::class.java,
                "database4.db").allowMainThreadQueries().build()
        var configuracionYCategorias :List<configuracionConCategorias> = db.configDataDao().getConfiguacionYCategorias()
        var categorias: ArrayList<String> = arrayListOf()
        configuracionYCategorias.forEach {
            it.categorias.forEach {
                        if(it.idConfig.equals(nuevoIdConfig)){
                            categorias.add(it.nombreCategoria)
                        }
                    }
        }


        var opcion = Opciones(categorias,dificultad,numPreguntas,false)

        var preg1 = opcion.preguntasDelJuego[0]
        txvCurrentQuestion.setText(preg1.pregunta)
        btnOp1.setText(preg1.respuestaCorrecta)
        btnOp2.setText(preg1.respuestasIncorrectas[0])
        btnOp3.setText(preg1.respuestasIncorrectas[1])
        btnOp4.setText(preg1.respuestasIncorrectas[2])

    }
}
