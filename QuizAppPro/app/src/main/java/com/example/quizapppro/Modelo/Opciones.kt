package com.example.quizapppro.Modelo

class Opciones(categorias:ArrayList<String>, dificultad:String,cantidadPreg:Int, statusPistas:Boolean) {

    var categorias: ArrayList<Categoria> = cargarCategorias(categorias)
    var dificultad: String = dificultad
    var cantidadPreguntas: Int = cantidadPreg
    var statusPistas: Boolean = statusPistas
    var preguntasDelJuego : ArrayList<Categoria.Pregunta> = cargarPreguntasDelJuego()

    private fun cargarCategorias(categorias: ArrayList<String>):ArrayList<Categoria>{
        var listaCategorias:ArrayList<Categoria> = arrayListOf()
        categorias.forEach {
            var categoria = Categoria(it)
            listaCategorias.add(categoria)
        }
        return listaCategorias
    }
    fun devolverPorNumeroPreguntas(numPreguntas: Int): MutableList<Categoria.Pregunta> {
        var preguntas = mutableListOf<Categoria.Pregunta>()
        categorias.forEach {
            TODO()
        }
        TODO()
    }
    private fun cargarPreguntasDelJuego():ArrayList<Categoria.Pregunta>{
        var preguntasDelJuego : ArrayList<Categoria.Pregunta> = arrayListOf()
        for (i in 0..cantidadPreguntas){
            var categoriaActual = categorias[i]
            var preguntaNueva = categoriaActual.devolverPreguntaAleatoria(preguntasDelJuego)
            preguntasDelJuego.add(preguntaNueva)
        }
        return preguntasDelJuego
    }




    }
