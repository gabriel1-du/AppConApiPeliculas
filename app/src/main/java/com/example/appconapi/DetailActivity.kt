package com.example.appconapi

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.appconapi.Network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bumptech.glide.Glide
import com.example.appconapi.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding // Declaración de la variable de binding

    override fun onCreate(savedInstanceState: Bundle?) { //start of line 19
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater) // Inflar usando View Binding
        setContentView(binding.root) // Establecer el root del binding como content view

        // Ya no necesitas findViewById, accedes a las vistas a través de 'binding'
        // ejemplo: binding.imagePoster, binding.textYear, binding.textActors

        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: ""

        if (movieTitle.isNotEmpty()) {
            fetchMovie(movieTitle)
        }
    } //end og the onCreate function

    private fun fetchMovie(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getMovie(title)

                withContext(Dispatchers.Main) {
                    Glide.with(this@DetailActivity)
                        .load(response.Poster)
                        .into(binding.imagePoster) // Usar binding.imagePoster

                    binding.textYear.text = "Año: ${response.Year}" // Usar binding.textYear
                    binding.textActors.text = "Actores: ${response.Actors}" // Usar binding.textActors
                }
            } catch (e: Exception) {
                // ...
            }
        }
    }
}
