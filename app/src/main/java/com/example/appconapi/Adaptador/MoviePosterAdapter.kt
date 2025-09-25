package com.example.appconapi.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.appconapi.R // Importa R de tu módulo principal
import com.example.appconapi.Data.MovieResponse // Importa tu clase Movie


/**
 * Adaptador para mostrar una lista de pósters de películas en un RecyclerView.
 *
 *
 * @param movieItems Lista inicial de objetos MovieResponse a mostrar.
 */
class MoviePosterAdapter(private var movieItems: List<MovieResponse>) :
    RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder>() {

    inner class MoviePosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //Imagen
        val posterImage: ImageView = itemView.findViewById(R.id.movie_poster_image)
        val titleText: TextView = itemView.findViewById(R.id.movie_title_text)
    }

    //funcion para crear la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val view = LayoutInflater.from(parent.context)
            //extrae imagen desde la actividad
            .inflate(R.layout.item_poster_layout, parent, false)
        return MoviePosterViewHolder(view)
    }



    //funcion que crea la vista en el recycle
    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val movie = movieItems[position]

        // Cargar imagen del póster con Glide
        Glide.with(holder.itemView.context)
            .load(movie.Poster)
            .into(holder.posterImage)

        // Establecer el título de la película
        // Verifica si el título es nulo o vacío si es necesario
        holder.titleText.text = movie.Title ?: "Título no disponible" // <-- AÑADE ESTO
    }

    override fun getItemCount(): Int {
        return movieItems.size
    }

    /**
     * Método para actualizar la lista de películas que muestra el adaptador.
     * @param newMovieItems La nueva lista de MovieResponse a mostrar.
     */
    fun updateMovies(newMovieItems: List<MovieResponse>) { // Cambiado a List<MovieResponse>
        movieItems = newMovieItems
        notifyDataSetChanged()
    }


}
