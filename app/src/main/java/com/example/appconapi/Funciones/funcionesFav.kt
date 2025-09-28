package com.example.appconapi.Funciones

import com.example.appconapi.Data.FavoriteManager
import com.example.appconapi.Data.MovieItem

fun searchInFavorites(query: String): List<MovieItem> {
    val favorites = FavoriteManager.favorites

    if (query.isBlank()) {
        return favorites // si está vacío, devolvemos todo
    }

    return favorites.filter { movie ->
        movie.Title.contains(query, ignoreCase = true) ||
                movie.Year.contains(query, ignoreCase = true)
    }
}