package com.example.quizapppro.Modelo

import android.graphics.Path
import org.json.JSONArray
import java.util.*

class Juego(porcentaje:Int, numPregunta:Int) {



    private var porcentajeActual:Int = porcentaje
        get() = field
        set(porcentaje) {field=porcentaje}
    private var numPregunta:Int = numPregunta
        get() = field
        set(numPregunta) {field=numPregunta}

    fun devolverPorNumeroPreguntas(numPreguntas : Int) {
        if (numPreguntas < 5) {
            TODO()
        }
        TODO()
    }


}
