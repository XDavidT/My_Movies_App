package com.android.academy.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.R
import com.android.academy.download.DownloadActivity
import com.android.academy.movie_data.AppDatabase
import com.android.academy.movie_data.MovieModel
import com.android.academy.networking.RestClient
import com.android.academy.networking.VideoResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_details_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val MOVIE_BUNDLE_KEY= "unique_movie_key"

class DetailsFragment : Fragment(){
    private lateinit var posterImage: ImageView
    private lateinit var coverImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var releaseDate: TextView
    private lateinit var trailerButton: Button
    private lateinit var overviewText: TextView
    private lateinit var trailerLink: String

    companion object{
        fun newInstance(movie: MovieModel): DetailsFragment{
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(MOVIE_BUNDLE_KEY,movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_details_fragment,container,false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie:MovieModel? = arguments?.getParcelable(MOVIE_BUNDLE_KEY)
        initViews(view)
        movie?.let(::loadMovie)

        //Move to youtube using link
        trailerButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(trailerLink)
            startActivity(intent)
        }

        //Start download
        details_cover_download.setOnClickListener {
            DownloadActivity.startActivity(context!!,movie!!)
        }
    }


    private fun loadMovie(movie:MovieModel){
        //Loading from URL
        Glide
            .with(this)
            .load(movie.imagePoster)
            .placeholder(R.drawable.progress_animation)
            .into(posterImage)
        Glide
            .with(this)
            .load(movie.imageCover)
            .placeholder(R.drawable.progress_animation)
            .into(coverImage)

        titleText.text = movie.name
        overviewText.text = movie.description

        trailerHandler(movie)
    }

    private fun initViews(view:View){
        posterImage = view.findViewById(R.id.posterImage)
        coverImage = view.findViewById(R.id.details_cover_image)
        titleText = view.findViewById(R.id.movie_title)
        releaseDate = view.findViewById(R.id.relaseDate)
        trailerButton = view.findViewById(R.id.trailer_button)
        overviewText = view.findViewById(R.id.overview)
    }

    private fun trailerHandler(movie: MovieModel){
//        Log.d("David","DetailsFragment->loadMovie (trailer sector)")

        //Try first to look in DB for the trailer
        context?.let {
            val trailerKey = AppDatabase
                .getInstance(it)
                ?.trailerDao()
                ?.getTrailer(movie.id)  //Call DB to find trailer match this Movie ID
            trailerKey?.let {videoModel->
                Log.d("DavidDB","Using DB link")
                trailerLink = videoModel.keyToUrl()
                trailerButton.isEnabled = true
                return
            }
        }

        //We didn't found so we moved here
        RestClient.getVideosToMovies(movie.id).enqueue(object : Callback<VideoResult> {

            override fun onFailure(call: Call<VideoResult>, t: Throwable) {
                Log.d("David", "Fail to get trailer link !")
            }

            override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) {
                if (response.isSuccessful) {
                    response.body()?.covnertToVideoModel().let{videoModel->
                        if(videoModel!= null){  //When found result, update the DB
                            context?.let {
                                AppDatabase
                                    .getInstance(it)
                                    ?.trailerDao()
                                    ?.insertTrailer(videoModel)
                            trailerLink = videoModel.keyToUrl()
                            trailerButton.isEnabled = true
                            }
                            Log.d("DavidDB","onResponse->Insert new link !")
                        }
                    }
                }
            }
        })
    }
}
