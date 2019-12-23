package com.android.academy.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.R
import com.android.academy.movie_model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(
 context: Context
){
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
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies_cache[position])
    }



    inner class ViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView){
        private val tvTitle: TextView = movieView.movieTitle
        private val ivPoster: ImageView = movieView.moviePosterView
        private val tvDesc:TextView = movieView.movieDesc

        fun bind(movieModel: MovieModel){
            ivPoster.setImageResource(movieModel.imageRes)
            tvTitle.text = movieModel.name
            tvDesc.text = movieModel.description
        }
    }

}
