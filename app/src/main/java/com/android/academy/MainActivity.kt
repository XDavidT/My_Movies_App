package com.android.academy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.android.academy.bg_service.activity_bgservice
import com.android.academy.threads.AsyncActivity
import com.android.academy.details.DetailActivity
import com.android.academy.list.MoviesFragment
import com.android.academy.list.OnMovieClickListener
import com.android.academy.movie_model.MovieModel
import com.android.academy.movie_model.MoviesContent
import com.android.academy.threads.ThreadsActivity
import com.android.academy.work_manager.WorkManagerActivity


class MainActivity : AppCompatActivity(), OnMovieClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moviesFragment = MoviesFragment()

        //Starting Activity using list of movies in fragment ( in avengers_main frame )
        supportFragmentManager.beginTransaction().
            add(R.id.activity_main_frame,moviesFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.thread_options,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_asytask_activity ->{
                val intent = Intent(this,
                    AsyncActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_thread_activity->{
                val intent = Intent(this,
                    ThreadsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_bgservice->{
                val intent = Intent(this,
                    activity_bgservice::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_workmanager->{
                val intent = Intent(this,
                    WorkManagerActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMovieClicked(movie:MovieModel) {
        DetailActivity.open(this, MoviesContent.movies.indexOfValue(movie))
//        val detailsFragment = DetailsFragment.newInstance(movie)
//        //When user click movie, change the details fragment
//        supportFragmentManager.beginTransaction().apply {
//         addToBackStack(null)
//         replace(R.id.activity_main_frame,detailsFragment)
//        }.commit()
    }

}