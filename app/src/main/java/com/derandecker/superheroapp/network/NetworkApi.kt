package com.derandecker.superheroapp.network

import retrofit2.http.GET
import com.derandecker.superheroapp.model.SuperHero
import retrofit2.http.Path

interface NetworkApi {
    @GET("{apikey}/{id}")
    suspend fun getUser(@Path ("apikey") apiKey: String, @Path("id") id: String): SuperHero
}
