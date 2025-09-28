package com.example.appconapi.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconapi.Adaptador.MovieAdapter
import com.example.appconapi.Data.FavoriteManager
import com.example.appconapi.Funciones.fetchMovie
import com.example.appconapi.R

class FavMovieActivity : AppCompatActivity() {
    //Inicializacion
    private lateinit var recyclerView: RecyclerView //guardadndo recycle
    private lateinit var adapter: MovieAdapter //guardando adaptador

    private lateinit var editTextMovie: EditText //busqueda de pelicula lateiinit var

    private lateinit var buttonSeeach : Button //boton de buscar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fav_movie)

        recyclerView = findViewById(R.id.recyclerViewFav)
        recyclerView.layoutManager = LinearLayoutManager(this)
        buttonSeeach = findViewById(R.id.buttonSearchFavMovie)

        // Cargar las películas desde FavoriteManager
        val favoriteMovies = FavoriteManager.favorites


        adapter = MovieAdapter(favoriteMovies) { movie -> //Carta que muestra pelicula
            // 👉 Redirigir a DetailActivity con los datos de la película
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movie.Title)
                putExtra("MOVIE_YEAR", movie.Year)
                putExtra("USE_YEAR_FILTER", true) // para que use fetchMovieAndYear
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        //Busqueda de pelicula favorito
        buttonSeeach.setOnClickListener {

        }
    }

}

