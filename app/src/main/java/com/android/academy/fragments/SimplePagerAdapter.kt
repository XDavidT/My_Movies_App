package com.android.academy.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.academy.movie_model.MovieContentManager

class SimplePagerAdapter ( mananger: FragmentManager):
        FragmentPagerAdapter(mananger) {
    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(MovieContentManager.movies[position])
    }

    override fun getCount(): Int {
        return MovieContentManager.movies.size
    }
}