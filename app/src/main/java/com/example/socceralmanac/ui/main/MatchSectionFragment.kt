package com.example.socceralmanac.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.lastMatch.LastMatchFragment
import com.example.socceralmanac.ui.nextMatch.NextMatchFragment
import kotlinx.android.synthetic.main.fragment_match_section.*

/**
 * A simple [Fragment] subclass.
 */
class MatchSectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_section, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as AppCompatActivity){
            val adapter = TabAdapter(
                    childFragmentManager)
            viewPagerMain.adapter = adapter


            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }

    }

    class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragments: List<Fragment> = ArrayList()
        private val tabName: Array<String> = arrayOf("Last Match", "Next Match")

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> LastMatchFragment()
            1 -> NextMatchFragment()
            else -> LastMatchFragment()
        }

        /*fun addFragment(fragment:Fragment, title:String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }*/

        override fun getCount(): Int = 2
        override fun getPageTitle(position: Int): CharSequence? = tabName[position]

    }

}
