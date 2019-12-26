package com.android.academy.list

import android.content.Context
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.R
import com.android.academy.movie_model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter(
    context: Context,
    private val movieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){
    private val movies_cache = mutableListOf<MovieModel>()

    fun setData(newItems: List<MovieModel>){
        movies_cache.clear()
        movies_cache.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies_cache.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false),
            movieClickListener
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies_cache[position])
    }



    inner class ViewHolder(movieView: View, movieClickListener: OnMovieClickListener)
        : RecyclerView.ViewHolder(movieView){
        private val tvTitle: TextView = movieView.movieTitle
        private val ivPoster: ImageView = movieView.moviePosterView
        private val tvDesc:TextView = movieView.movieDesc
        private lateinit var movieModel: MovieModel


        init {
            movieView.setOnClickListener {
                movieClickListener.onMovieClicked(movieModel)
            }
        }
        fun bind(movieModel: MovieModel){
            ivPoster.setImageResource(movieModel.imageRes)
            tvTitle.text = movieModel.name
            tvDesc.text = movieModel.description
            this.movieModel = movieModel
        }
    }

}
