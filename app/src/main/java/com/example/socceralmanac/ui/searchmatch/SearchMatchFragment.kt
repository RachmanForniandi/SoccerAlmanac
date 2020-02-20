package com.example.socceralmanac.ui.searchmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.adapters.SearchMatchAdapter
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.models.search.EventItem
import com.example.socceralmanac.models.search.ResponseSearch
import com.example.socceralmanac.ui.detailMatch.MatchDetailActivity
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.search_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import javax.xml.transform.Templates

class SearchMatchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMatchFragment()
    }

    private lateinit var viewModel: SearchMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchMatchViewModel::class.java)

        acSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.lookForTheMatch(p0.toString())
            }
        })

        searchObserver()
    }

    private fun searchObserver() {
        viewModel.responseSearchOfMatch.observe(viewLifecycleOwner, Observer {showResponseSearch(it)})
        viewModel.isLoadingSearch.observe(viewLifecycleOwner, Observer {showLoadingSearch(it)})
    }

    private fun showLoadingSearch(it: Boolean?) {
        if (it ?: false)pbSearch.show()else pbSearch.hide()
    }

    private fun showResponseSearch(it: ResponseTimeMatch?) {
        searchListMatchOfTeam.adapter = MatchAdapter(it?.events,object :MatchAdapter.onClickItem{
            override fun matchClick(item: EventsTime?) {
                startActivity<MatchDetailActivity>(
                    "detailMatch" to item
                )
            }
        })
    }

}
