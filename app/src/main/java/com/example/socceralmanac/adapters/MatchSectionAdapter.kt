package com.example.socceralmanac.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MatchSectionAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragmentPage = arrayListOf<Fragment>()

    var titlePage = arrayListOf<String>()

    fun setupFragment(fragment: Fragment, title: String) {
        fragmentPage.add(fragment)
        titlePage.add(title)
    }

    override fun getItem(position: Int)=fragmentPage[position]

    override fun getCount(): Int {
        return fragmentPage.size
    }

    override fun getPageTitle(position: Int)=titlePage[position]

}