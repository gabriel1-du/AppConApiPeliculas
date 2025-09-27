package com.example.appconapi.Adaptador


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appconapi.R
import com.example.appconapi.Data.MovieItem  // ajusta la ruta del modelo

class MovieAdapter(
    private val movies: List<MovieItem>,
    private val onItemClick: (MovieItem) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imagePoster)
        val title: TextView = itemView.findViewById(R.id.textTitle)
        val year: TextView = itemView.findViewById(R.id.textYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.Title
        holder.year.text = movie.Year

        // Cargar imagen con Glide
        Glide.with(holder.itemView.context)
            .load(movie.Poster)
            .placeholder(R.drawable.ic_placeholder) // 🔹 agrega este drawable en res/drawable
            .error(R.drawable.ic_placeholder)       // fallback si no carga
            .into(holder.poster)

        // Click en la tarjeta
        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}