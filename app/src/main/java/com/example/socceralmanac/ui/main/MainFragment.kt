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
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        setHasOptionsMenu(true)

        with(activity as AppCompatActivity){
            setSupportActionBar(toolbar_match)
            viewPagerMain.adapter = CustomViewPagerAdapter(childFragmentManager, mapOf(
                getString(R.string.last_match) to LastMatchFragment(),
                getString(R.string.next_match) to NextMatchFragment()
            ))
            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }

    }

    /*class TabAdapter(sfm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(sfm,behavior) {

        private val tabName : Array<String> = arrayOf("Last Match","Next Match" )

        override fun getItem(position: Int): Fragment = when(position) {
            0 -> LastMatchFragment()
            1 -> NextMatchFragment()
            else -> LastMatchFragment()

        }
        override fun getCount(): Int =2
        override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)

    }*/



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_search).title = getString(R.string.match_search)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.action_search_view->{
                startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }



    /*private fun searchObserver() {
        viewModel.responseSearchOfMatch.observe(viewLifecycleOwner, Observer {showResponseSearch(it)})
        viewModel.isLoadingSearch.observe(viewLifecycleOwner, Observer {showLoadingSearch(it)})
    }

    private fun showLoadingSearch(it: Boolean?) {
        if (it ?: false)pbSearch.show()else pbSearch.hide()
    }

    private fun showResponseSearch(it: ResponseTimeMatch?) {
        searchListMatchOfTeam.adapter = MatchAdapter(it?.events,object : MatchAdapter.onClickItem{
            override fun matchClick(item: EventsTime?) {
                startActivity<MatchDetailActivity>(
                    "detailMatch" to item
                )
            }
        })
    }*/

}
