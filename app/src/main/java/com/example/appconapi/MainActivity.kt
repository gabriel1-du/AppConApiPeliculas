package com.example.appconapi


import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMovie = findViewById<EditText>(R.id.editTextMovie)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        buttonSearch.setOnClickListener {
            val movieTitle = editTextMovie.text.toString().trim()

            if (movieTitle.isNotEmpty()) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("MOVIE_TITLE", movieTitle)
                startActivity(intent)
            }
        }

    } //Fin
}