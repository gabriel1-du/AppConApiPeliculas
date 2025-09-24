package com.example.appconapi


import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMovie = findViewById<EditText>(R.id.editTextMovie) //Nombre pelicula
        val edtiTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch) //Boton de busqueda
        val checkBox = findViewById<CheckBox>(R.id.checkboxYearFilter) //Checkbox

        buttonSearch.setOnClickListener {
            //Variables para guardar los datos ingresados
            val movieTitle = editTextMovie.text.toString().trim() //Nombre de la pelicula
            val year = edtiTextYear.text.toString().trim() //Año de la pelicula

            if (movieTitle.isNotEmpty()) { //verificacion del campo nombre de pelicula
                if (checkBox.isChecked) { // verificacion de checkbox
                    if (year.isNotEmpty()) {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("MOVIE_TITLE", movieTitle)
                        intent.putExtra("MOVIE_YEAR", year)
                        startActivity(intent)
                    } else {
                        // CheckBox está marcado pero sin año =
                        Toast.makeText(this, "Por favor, ingresa un año si el filtro está activado.", Toast.LENGTH_SHORT).show()
                    }
                } else { // Si el checkbox no está marcado
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("MOVIE_TITLE", movieTitle)
                    startActivity(intent)
                }
            } else {
                // Mensaje de que el texto este vacio
                Toast.makeText(this, "Por favor, ingresa el título de la película.", Toast.LENGTH_SHORT).show()
            }
        } // fin button search
    } // fin
}
