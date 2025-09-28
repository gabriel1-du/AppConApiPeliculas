package com.example.appconapi.Funciones

import com.example.appconapi.Data.MovieItem

fun addMovieToFav(movieItem : MovieItem, favoriteList : MutableList<MovieItem>){

    if(!favoriteList.contains(movieItem)){
        favoriteList.add(movieItem)
        print("Se a agregado la pelicula : ${movieItem.Title}")

    }else{
        print("La pelicula ya se encuentra en la lista")

    }
}