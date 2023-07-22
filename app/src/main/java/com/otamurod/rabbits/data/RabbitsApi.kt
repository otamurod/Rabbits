package com.otamurod.rabbits.data

import retrofit2.http.GET

interface RabbitsApi {
    @GET("random-rabbit")
    suspend fun getRandomRabbit(): Rabbit
}