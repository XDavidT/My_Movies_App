package com.android.academy.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimplePagerAdapter ( mananger: FragmentManager, val fragments: List<Fragment>):
        FragmentPagerAdapter(mananger) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}