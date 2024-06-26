package com.example.magicapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicApiService {
    @GET("v1/cards")
    suspend fun getCardByName(@Query("name") name: String): Response<CardResponse>
}
