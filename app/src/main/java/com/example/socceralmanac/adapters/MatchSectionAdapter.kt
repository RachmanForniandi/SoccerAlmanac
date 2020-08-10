package com.example.socceralmanac.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.socceralmanac.ui.lastMatch.LastMatchFragment
import com.example.socceralmanac.ui.nextMatch.NextMatchFragment

class MatchSectionAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    /*var fragmentPage = arrayListOf<Fragment>()

    var titlePage = arrayListOf<String>()

    fun setupFragment(fragment: Fragment, title: String) {
        fragmentPage.add(fragment)
        titlePage.add(title)
    }*/
    private val tabName: Array<String> = arrayOf("Last Match", "Next Match")

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> LastMatchFragment()
        1 -> NextMatchFragment()
        else -> LastMatchFragment()

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? = tabName[position]

}