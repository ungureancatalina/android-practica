package com.example.moviesxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesxml.data.*

class MovieAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieVH>() {

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvGenre: TextView = itemView.findViewById(R.id.tvGenre)
        val tvYear: TextView = itemView.findViewById(R.id.tvYear)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieVH(v)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvGenre.text = item.genre
        holder.tvYear.text = item.year.toString()
        holder.tvRating.text = "★ ${item.rating}"
        holder.itemView.setOnClickListener { onItemClick(item) } // click pe item
    }

    override fun getItemCount(): Int = items.size
}
