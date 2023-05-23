package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

        private val URL_BASE = "https://rickandmortyapi.com/api/"

        //guarda a conexao com o servidor da API
        private val retrofitFactory = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create()) //criar fabrica de conversao
            .build()

        fun getCharacterService(): CharacterService{
            return retrofitFactory.create(CharacterService::class.java)
        }
}