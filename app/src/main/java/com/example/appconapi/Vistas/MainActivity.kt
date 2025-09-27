package com.example.appconapi.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconapi.Adaptador.MovieAdapter
import com.example.appconapi.Data.MovieItem
import com.example.appconapi.Network.RetrofitInstance
import com.example.appconapi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<MovieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMovie = findViewById<EditText>(R.id.editTextMovie)
        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MovieAdapter(movieList) { movie ->
            // Al hacer clic → abrir DetailActivity
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MOVIE_TITLE", movie.Title)
            intent.putExtra("MOVIE_YEAR", movie.Year)
            intent.putExtra("USE_YEAR_FILTER", true)
            startActivity(intent)
        }
        recyclerView.adapter = adapter


        // Botón buscar
        buttonSearch.setOnClickListener {
            val movieTitle = editTextMovie.text.toString().trim()

            if (movieTitle.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = RetrofitInstance.api.searchMovies(movieTitle)

                        withContext(Dispatchers.Main) {
                            if (response.Response == "True" && response.Search != null) {
                                movieList.clear()
                                movieList.addAll(response.Search)
                                adapter.notifyDataSetChanged()
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "No se encontraron resultados",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@MainActivity,
                                "Error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa el título de la película.", Toast.LENGTH_SHORT).show()
            }
        }
        }
}