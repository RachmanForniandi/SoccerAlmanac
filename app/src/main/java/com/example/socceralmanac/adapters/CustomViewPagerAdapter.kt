package com.example.socceralmanac.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomViewPagerAdapter(manager:FragmentManager, val map:Map<String, Fragment>) : FragmentPagerAdapter(manager){

    private val tabPages = map.values.toList()
    private val titles = map.keys.toList()

    override fun getItem(position: Int): Fragment = tabPages[position]

    override fun getCount(): Int = map.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> titles[0]
            else -> titles[1]
        }
    }
}