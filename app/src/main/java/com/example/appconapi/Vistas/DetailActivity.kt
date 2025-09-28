package com.example.appconapi.Vistas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appconapi.Data.FavoriteManager
import com.example.appconapi.Data.MovieItem
import com.example.appconapi.Data.MovieResponse
import com.example.appconapi.Funciones.fetchMovie
import com.example.appconapi.Funciones.fetchMovieAndYear
import com.example.appconapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var currentMovieResponse: MovieResponse? = null // <- aquí guardamos la película cargada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: ""
        val movieYear = intent.getStringExtra("MOVIE_YEAR") ?: ""
        val useYearFilter = intent.getBooleanExtra("USE_YEAR_FILTER", false)

        if (movieTitle.isNotEmpty()) {
            if (useYearFilter && movieYear.isNotEmpty()) {
                fetchMovieAndYear(this, binding, movieTitle, movieYear) { response ->
                    currentMovieResponse = response
                }
            } else {
                fetchMovie(this, binding, movieTitle) { response ->
                    currentMovieResponse = response
                }
            }
        }

        binding.buttonaddFav.setOnClickListener {
            val responseMovie = currentMovieResponse
            if (responseMovie != null) {
                val movieItem = MovieItem(
                    Title = responseMovie.Title,
                    Year = responseMovie.Year,
                    imdbID = responseMovie.imdbID ?: "tt${System.currentTimeMillis()}",
                    Type = "movie",
                    Poster = responseMovie.Poster
                )

                FavoriteManager.addMovie(movieItem)
                Toast.makeText(this, "${responseMovie.Title} añadida a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Primero busca una película", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
