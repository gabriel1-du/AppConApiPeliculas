package com.example.appconapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMovie = findViewById<EditText>(R.id.editTextMovie) // Nombre de la película
        val editTextYear = findViewById<EditText>(R.id.editTextYear)   // Año de la película
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)     // Botón de búsqueda
        val checkBox = findViewById<CheckBox>(R.id.checkboxYearFilter) // Checkbox

        // Mostrar u ocultar el campo de año según el estado del CheckBox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            editTextYear.visibility = if (isChecked) View.VISIBLE else View.GONE //Cambio visibilidad
        }

        buttonSearch.setOnClickListener {

            // Variables para guardar los datos ingresados
            val movieTitle = editTextMovie.text.toString().trim() //guarda el titulo
            val year = editTextYear.text.toString().trim() //guarda el año

            if (movieTitle.isNotEmpty()) { // Verificación del campo nombre
                if (checkBox.isChecked) { // Si el filtro de año está activado
                    if (year.isNotEmpty()) {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("MOVIE_TITLE", movieTitle)
                        intent.putExtra("MOVIE_YEAR", year)
                        intent.putExtra("USE_YEAR_FILTER", true) ////aplicacion de filtro
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Por favor, ingresa un año si el filtro está activado.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else { // Si el checkbox no está marcado
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("MOVIE_TITLE", movieTitle)
                    intent.putExtra("USE_YEAR_FILTER", false)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(
                    this,
                    "Por favor, ingresa el título de la película.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}