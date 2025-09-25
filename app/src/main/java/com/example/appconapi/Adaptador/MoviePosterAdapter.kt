package com.example.appconapi.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val view = LayoutInflater.from(parent.context)
            //extrae imagen desde la actividad
            .inflate(R.layout.item_poster_layout, parent, false)
        return MoviePosterViewHolder(view)
    }
    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val movie = movieItems[position] // Ahora es un objeto MovieResponse

        // Usar Glide para cargar la imagen desde la URL en el ImageView
        // Accede al campo 'Poster' de tu MovieResponse
        Glide.with(holder.itemView.context)
            .load(movie.Poster) // <-- CAMBIO AQUÍ: usa movie.Poster
            .into(holder.posterImage)
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
