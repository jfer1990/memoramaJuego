package com.example.quizapppro.Modelo
import android.content.res.Resources
import com.example.quizapppro.R
import kotlin.random.Random

class Categoria(nombreCategoria:String) {

    private var name: String = nombreCategoria
    private var listaPreguntas: ArrayList<Pregunta> = cargarPreguntas()
        get() = field

    inner class Pregunta(pregunta: String, respCorrecta: String, respuestasIncorrectas: ArrayList<String>) {
        var pregunta: String = pregunta
            get() = field
        var respuestaCorrecta: String = respCorrecta
            get() = field
        var respuestasIncorrectas: ArrayList<String> = respuestasIncorrectas
            get() = field
    }

    fun cargarPreguntas(): ArrayList<Pregunta> {
        var preguntas: Array<String> = arrayOf()
        if (name.equals("geografia")) {
            preguntas = Resources.getSystem().getStringArray(R.array.geografia)
        }
        if (name.equals("arte")) {
            preguntas = Resources.getSystem().getStringArray(R.array.arte)
        }
        if (name.equals("cultura")) {
            preguntas = Resources.getSystem().getStringArray(R.array.cultura)
        }
        if (name.equals("deportes")) {
            preguntas = Resources.getSystem().getStringArray(R.array.deportes)
        }
        if (name.equals("tecnologia")) {
            preguntas = Resources.getSystem().getStringArray(R.array.tecnologia)
        }
        if (name.equals("catafixia")) {
            preguntas = Resources.getSystem().getStringArray(R.array.catafixia)
        }
        var retornoPreguntas: ArrayList<Pregunta> = arrayListOf()
        for (i in 0..(preguntas.size - 1) step 5) {
            var respuestasIncorrectas = arrayListOf<String>(preguntas[2], preguntas[3], preguntas[4])
            var pregunta = Pregunta(preguntas[0], preguntas[1], respuestasIncorrectas)
            retornoPreguntas.add(pregunta)
        }
        return retornoPreguntas

    }
    fun devolverPreguntaAleatoria(preguntasYaHechas: MutableList<Pregunta>?):Pregunta{
        //[p1, p2,..pn]
        var tamanoArregloPreguntas :Int = listaPreguntas.size-1 // Conteo del arreglo en logica 0
        var indiceAleatorio = Random.nextInt(0, tamanoArregloPreguntas)
        var preguntaAleatoria:Pregunta = listaPreguntas[indiceAleatorio]

        if(preguntasYaHechas==null){
            return preguntaAleatoria
        }
        else {
            while (true) {
                if (preguntaEsRepetida(preguntasYaHechas, preguntaAleatoria.pregunta)) {
                    indiceAleatorio = Random.nextInt(0, tamanoArregloPreguntas)
                    preguntaAleatoria = listaPreguntas[indiceAleatorio]
                } else {
                    return preguntaAleatoria
                }

            }
        }

    }
    private fun preguntaEsRepetida(listaPreguntas:MutableList<Pregunta>,preguntaComparadora:String):Boolean{
        listaPreguntas.forEach{
            if(it.pregunta.equals(preguntaComparadora)){
                return true
            }
        }
        return false
    }


}
