package br.senai.sp.jandira.lionschool.model

data class Character(
    val id: Long,
    val nome: String,
    val matricula: Int,
    val sexo: String,
    val status: String,
    val foto: String
)
