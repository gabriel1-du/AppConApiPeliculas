package com.example.appconapi.Network


import com.example.appconapi.Data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.appconapi.BuildConfig
import com.example.appconapi.Data.SearchResponse
interface OmdbApi {

    @GET("/") // Endpoint para buscar una película por título
    suspend fun getMovie(
        @Query("t") title: String, // Título de la película
        @Query("y") year: String?, // Año opcional
        @Query("apikey") apiKey: String = BuildConfig.OMDB_API_KEY // Clave de API

    ): MovieResponse

    @GET("/")
    suspend fun searchMovies(
        @Query("s") title: String,
        @Query("apikey") apiKey: String = BuildConfig.OMDB_API_KEY
    ): SearchResponse
}



