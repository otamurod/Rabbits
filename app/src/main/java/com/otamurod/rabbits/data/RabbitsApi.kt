package com.otamurod.rabbits.data

import retrofit2.http.GET

interface RabbitsApi {
    @GET("random-rabbit")
    suspend fun getRandomRabbit(): Rabbit

    companion object{
        const val BASE_URL = "http://192.168.43.89:8080/"
    }
}