package com.example.appconapi.Funciones


import android.content.Context
import com.bumptech.glide.Glide //para extraer el poster de la url
import com.example.appconapi.Data.MovieResponse
import com.example.appconapi.Network.RetrofitInstance
import com.example.appconapi.databinding.ActivityDetailBinding //interaccion con otros elementos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// Mostrar película
fun showMovie(context: Context, binding: ActivityDetailBinding, response: MovieResponse) {
    Glide.with(context)
        .load(response.Poster)
        .into(binding.imagePoster)

    binding.textYear.text = "Año: ${response.Year ?: "N/A"}"
    binding.textActors.text = "Actores: ${response.Actors ?: "N/A"}"
}

// Buscar solo por título
fun fetchMovie(context: Context, binding: ActivityDetailBinding, title: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.getMovie(title, null)

            withContext(Dispatchers.Main) {
                showMovie(context, binding, response)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                binding.textYear.text = "Error: ${e.message}"
            }
        }
    }
}

// Buscar por título y año
fun fetchMovieAndYear(context: Context, binding: ActivityDetailBinding, title: String, year: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.getMovie(title, year)

            withContext(Dispatchers.Main) {
                if (response.Response == "True") {
                    Glide.with(context)
                        .load(response.Poster)
                        .into(binding.imagePoster)

                    binding.textYear.text = "Año: ${response.Year}"
                    binding.textActors.text = "Actores: ${response.Actors}"
                } else {
                    binding.textYear.text = "No se encontró la película para '$title' en el año $year"
                    binding.textActors.text = ""
                    binding.imagePoster.setImageDrawable(null)
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                binding.textYear.text = "Error: ${e.message}"
            }
        }
    }
}