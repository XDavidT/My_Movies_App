package com.android.academy.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.academy.movie_model.MoviesContent

class SimplePagerAdapter ( mananger: FragmentManager):
        FragmentPagerAdapter(mananger) {
    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(MoviesContent.movies[position])
    }

    override fun getCount(): Int {
        return MoviesContent.movies.size
    }
}