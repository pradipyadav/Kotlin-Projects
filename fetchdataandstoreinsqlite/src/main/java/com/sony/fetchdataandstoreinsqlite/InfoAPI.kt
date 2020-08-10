package com.sony.fetchdataandstoreinsqlite

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface InfoAPI {


    @GET("users")
    fun getInfo(): Call<List<InfoData>>

    companion object {
        operator fun invoke(): InfoAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InfoAPI::class.java)
        }
    }
}