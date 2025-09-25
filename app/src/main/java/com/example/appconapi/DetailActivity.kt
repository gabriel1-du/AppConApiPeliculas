package com.example.appconapi

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.appconapi.Funciones.fetchMovie
import com.example.appconapi.Funciones.fetchMovieAndYear
import com.example.appconapi.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding // Declaración de la variable de binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater) // Inflar usando View Binding
        setContentView(binding.root) // Establecer el root del binding como content view


        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: ""
        val movieYear = intent.getStringExtra("MOVIE_YEAR") ?: ""
        val useYearFilter = intent.getBooleanExtra("USE_YEAR_FILTER", false)

        if (movieTitle.isNotEmpty()) {
            if (useYearFilter && movieYear.isNotEmpty()) {
                fetchMovieAndYear(this, binding, movieTitle, movieYear)
            } else {
                fetchMovie(this, binding, movieTitle)
            }
        }
    }//fin on create

}
