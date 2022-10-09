package com.example.challenge5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge5.R
import com.example.challenge5.databinding.ItemMovieBinding
import com.example.challenge5.model.GetNowPlayingResponseItem

class MovieAdapter(
    private val listMovie: ArrayList<GetNowPlayingResponseItem>
):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView:ItemMovieBinding):RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
        fun bind(movieData: GetNowPlayingResponseItem){
            with(binding){
                Glide.with(itemView)
                    .load(IMAGE_BASE + movieData.posterPath)
                    .into(imgPoster)
                tvTitle.text = movieData.title
                tvTahun.text = movieData.releaseDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}