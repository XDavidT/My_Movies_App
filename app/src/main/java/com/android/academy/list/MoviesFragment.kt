package com.android.academy.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.academy.R
import com.android.academy.movie_data.AppDatabase
import com.android.academy.movie_data.MoviesContent
import com.android.academy.movie_data.MovieModel
import com.android.academy.networking.MoviesRootResult
import com.android.academy.networking.RestClient
import kotlinx.android.synthetic.main.movies_rv_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment(), OnMovieClickListener {
    private var listener: OnMovieClickListener? = null
    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_rv_fragment, container, false)

        Log.d("David", "MoviesFragment->onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovies()
        Log.d("David", "MoviesFragment->onViewCreated")

    }

    override fun onMovieClicked(movie: MovieModel) {
        listener?.onMovieClicked(movie)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieClickListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initRecyclerView() {
        Log.d("David", "Init recycler view")
        movies_fragment_rcv.layoutManager = LinearLayoutManager(context)
        movieAdapter = MoviesAdapter(this@MoviesFragment,this@MoviesFragment)
        movies_fragment_rcv.adapter = movieAdapter
        Log.d("David","initRecyclerView -> Set Data")
        movieAdapter.setData(MoviesContent.getMoviesList())
    }

    private fun loadMovies() {
        Log.d("David", "loadMovies")

        //Asking API to get the data
        RestClient.getPopularMovies()
            .enqueue(object : Callback<MoviesRootResult> {

                //First option, the request failed
                override fun onFailure(call: Call<MoviesRootResult>, t: Throwable) {
                    Log.d("David", "Fail !")
                }

                //Second option, the request made
                override fun onResponse(
                    call: Call<MoviesRootResult>,
                    response: Response<MoviesRootResult>
                ) {

                    //Be sure we have response from server
                    if (response.isSuccessful) {
                        response.body()?.results?.let{
                            Log.d("David", "MoviesFragment ->loadMovies->onResponse->isSuccessful")
                            val list = SparseArray<MovieModel>()
                            it.forEach {moviesResult->
                                list.append(moviesResult.id,moviesResult.toMovieModel())
                            }
                            MoviesContent.addMovieList(list) }
                    }
                    initRecyclerView()
                }
            })
        AppDatabase.getInstance(context!!)?.movieDao()?.insertAll(MoviesContent.getMoviesList())
        Log.d("David","**Insert to db complete !**")
    }
}