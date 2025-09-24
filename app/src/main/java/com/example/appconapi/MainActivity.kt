package com.example.appconapi


import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMovie = findViewById<EditText>(R.id.editTextMovie) //Nombre pelicula
        val edtiTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch) //Boton de busqueda

        buttonSearch.setOnClickListener {
            //Variables para guardar los datos ingresados
            val movieTitle = editTextMovie.text.toString().trim() //Nombre de la pelicula
            val year = edtiTextYear.text.toString().trim() //Año de la pelicula
            val checkBox = findViewById<CheckBox>(R.id.checkboxYearFilter) //Checkbox


        }

    } //Fin
}