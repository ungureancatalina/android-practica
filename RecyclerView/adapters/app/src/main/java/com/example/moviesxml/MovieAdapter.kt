package com.example.moviesxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapterul primeste o lista de filme si un callback pentru click pe item
class MovieAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // ViewHolder tine referinte la subview-urile din item_movie.xml
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvGenreYear: TextView = itemView.findViewById(R.id.tvGenreYear)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    // Layoutul de item si un ViewHolder nou (doar cand e nevoie)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    // Legarea datelor pentru pozitia curenta in ViewHolder-ul dat
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]

        // Setarea textelor pe Views
        holder.tvTitle.text = movie.title
        holder.tvGenreYear.text = "${movie.genre} • ${movie.year}"
        holder.tvRating.text = "⭐ ${"%.1f".format(movie.rating)}"

        // Cclick pe intregul item (card)
        holder.itemView.setOnClickListener {
            // La click, apelam callback-ul si trimitem obiectul Movie
            onItemClick(movie)
        }
    }

    // Cate elemente are lista
    override fun getItemCount(): Int = items.size
}