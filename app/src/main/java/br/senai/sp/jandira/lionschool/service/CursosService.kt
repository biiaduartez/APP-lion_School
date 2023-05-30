package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CursosList
import retrofit2.Call
import retrofit2.http.GET

interface CursosService {
    @GET("cursos")
    fun getCursos(): Call<CursosList>

}