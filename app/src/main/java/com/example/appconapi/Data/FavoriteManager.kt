package com.example.appconapi.Data

object FavoriteManager {
    val favorites: MutableList<MovieItem> = mutableListOf()

    fun addMovie(movie: MovieItem) {
        if (!favorites.any { it.imdbID == movie.imdbID }) {
            favorites.add(movie)
        }
    }
}