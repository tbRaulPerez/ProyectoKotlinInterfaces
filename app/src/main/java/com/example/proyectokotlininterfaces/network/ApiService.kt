package com.example.proyectokotlininterfaces.network

import com.example.proyectokotlininterfaces.model.MonsterResponse
import com.example.proyectokotlininterfaces.model.SpellResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET

interface ApiService {
    @GET("monsters/")
    suspend fun getMonsters(): MonsterResponse

    @GET("spells/")
    suspend fun getSpells(): SpellResponse


    companion object{
        var apiService: ApiService? = null

        fun getInstance(): ApiService{
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.open5e.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}