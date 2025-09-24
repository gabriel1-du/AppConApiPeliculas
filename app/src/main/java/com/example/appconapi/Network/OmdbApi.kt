package com.example.appconapi.Network


import com.example.appconapi.Data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.appconapi.BuildConfig

interface OmdbApi {

    @GET("/")
    suspend fun getMovie(
        @Query("t") title: String,
        @Query("apikey") apiKey: String = BuildConfig.OMDB_API_KEY

    ): MovieResponse
}