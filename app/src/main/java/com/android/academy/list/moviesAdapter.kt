package com.android.academy.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.R
import com.android.academy.movie_model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

private class MoviesDiffUtilCallback : DiffUtil.ItemCallback<MovieModel>(){
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }

}

class MoviesAdapter(
    private val movieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    private val asyncListDiffer = AsyncListDiffer<MovieModel>(this,MoviesDiffUtilCallback())

    fun setData(newItems: List<MovieModel>){
//        Log.d("David","setData: ${newItems.size}")
        asyncListDiffer.submitList(newItems)
//        Log.d("David","setData: Done")
    }

    override fun getItemCount(): Int {
//        Log.d("David","getItemCount: ${asyncListDiffer.currentList.size}")

        return asyncListDiffer.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        Log.d("David","onCreateViewHolder")

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false),
            movieClickListener
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieModel = asyncListDiffer.currentList[position]
        holder.bind(movieModel)
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
