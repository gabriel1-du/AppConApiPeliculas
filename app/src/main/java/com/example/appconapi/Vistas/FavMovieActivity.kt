package com.example.appconapi.Vistas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconapi.Adaptador.MovieAdapter
import com.example.appconapi.Data.FavoriteManager
import com.example.appconapi.R

class FavMovieActivity : AppCompatActivity() {
    //Inicializacion
    private lateinit var recyclerView: RecyclerView //guardadndo recycle
    private lateinit var adapter: MovieAdapter //guardando adaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fav_movie)

        recyclerView = findViewById(R.id.recyclerViewFav)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar las películas desde FavoriteManager
        val favoriteMovies = FavoriteManager.favorites

        // Pasamos la lista y definimos qué hacer al hacer click
        adapter = MovieAdapter(favoriteMovies) { movie ->
            // Aquí decides qué pasa cuando tocas un favorito
            Toast.makeText(this, "Seleccionaste: ${movie.Title}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }

}

