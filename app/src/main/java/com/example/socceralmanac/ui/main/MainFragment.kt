package com.example.socceralmanac.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer

import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.CustomViewPagerAdapter
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.ui.detailMatch.MatchDetailActivity
import com.example.socceralmanac.ui.lastmatch.LastMatchFragment
import com.example.socceralmanac.ui.nextmatch.NextMatchFragment
import com.example.socceralmanac.ui.searchmatch.SearchMatchActivity
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.search_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container_main, false)
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        setHasOptionsMenu(true)
        with(activity as AppCompatActivity){
            //setSupportActionBar(toolbar_match)
            viewPagerMain.adapter = CustomViewPagerAdapter(childFragmentManager, mapOf(
                getString(R.string.last_match) to LastMatchFragment(),
                getString(R.string.next_match) to NextMatchFragment()
            ))
            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_search).title = getString(R.string.match_search)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.action_search->{
                startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
