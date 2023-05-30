package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.AlunosList
import retrofit2.Call
import retrofit2.http.GET

interface AlunosService {

    // retorna TODOS os alunos
    @GET("/v1/lion-school/alunos")
    fun getTodosAlunos(): Call<AlunosList>


   // @GET("/v1/lion-school/alunos")
}