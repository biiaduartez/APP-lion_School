package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

        private val URL_CURSOS = "https://backlion-school-production.up.railway.app/v1/lion-school/"


        //guarda a conexao com o servidor da API
        private val retrofitFactory = Retrofit
            .Builder()
            .baseUrl(URL_CURSOS)
            .addConverterFactory(GsonConverterFactory.create()) //criar fabrica de conversao
            .build()

    fun getAlunosService(): AlunosService{
        return retrofitFactory.create(AlunosService::class.java)
    }

    fun getCursoService(): CursosService{
        return retrofitFactory.create(CursosService::class.java)
    }

    fun getDisciplinasService(): DisciplinaService{
        return retrofitFactory.create(DisciplinaService::class.java)
    }
}
