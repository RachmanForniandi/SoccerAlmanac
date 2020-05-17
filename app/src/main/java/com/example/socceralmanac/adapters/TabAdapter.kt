package com.example.socceralmanac.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.socceralmanac.ui.lastMatch.LastMatchFragment
import com.example.socceralmanac.ui.nextMatch.NextMatchFragment

class TabAdapter(fm: FragmentManager,private val map: Map<String, Fragment>) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val pages = map.values.toList()
    private val titleOfPage = map.keys.toList()

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = map.size
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
        0-> titleOfPage[0]
        else->titleOfPage[1]
    }
    }

}