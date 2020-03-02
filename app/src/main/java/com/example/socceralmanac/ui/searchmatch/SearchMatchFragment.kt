package com.example.socceralmanac.ui.searchmatch

import android.app.SearchManager
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.adapters.SearchMatchAdapter
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.models.search.EventItem
import com.example.socceralmanac.models.search.ResponseSearch
import com.example.socceralmanac.ui.detailMatch.MatchDetailActivity
import com.example.socceralmanac.utility.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.search_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import javax.xml.transform.Templates

class SearchMatchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMatchFragment()
    }

    private lateinit var viewModel: SearchMatchViewModel
    private var queryKeywordTeam:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        with(activity as AppCompatActivity){
            setSupportActionBar(acSearch)
        }
        viewModel = ViewModelProviders.of(this).get(SearchMatchViewModel::class.java)
        viewModel.lookForTheMatch(queryKeywordTeam)

        searchObserver()
    }

    private fun searchObserver() {
        viewModel.responseSearchOfMatch.observe(viewLifecycleOwner, Observer {showResponseSearch(it)})
        viewModel.isLoadingSearch.observe(viewLifecycleOwner, Observer {showLoadingSearch(it)})
        viewModel.apiError.observe(viewLifecycleOwner, Observer {showErrorSearch(it)})
    }

    private fun showErrorSearch(it: Throwable?) {
        //Toast.makeText(activity,it?.message ?: "",Toast.LENGTH_SHORT).show()
        showTextErrorSearch("Sorry No Result for" + "'$queryKeywordTeam'" +
                "\n $it")
        img_background_search.visible()
    }

    private fun showTextErrorSearch(msg: String) {
        txt_error_msg.text = msg
        txt_error_msg.visible()
    }

    private fun hideErrorMessageSearch() {
        if (txt_error_msg != null){
            txt_error_msg.text = ""
            txt_error_msg.invisible()
        }
    }

    private fun showLoadingSearch(it: Boolean?) {
        if (it ?: false)pbSearch.show()else pbSearch.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.action_search_view)?.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint =getString(R.string.search_your_team)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryKeywordTeam = query.toString()
                menu.findItem(R.id.action_search_view).collapseActionView()
                searchView.setQuery(queryKeywordTeam,true)
                viewModel.lookForTheMatch(queryKeywordTeam)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                queryKeywordTeam = newText.toString()
                if (queryKeywordTeam.isEmpty()){
                    img_background_search.visible()
                }else{
                    img_background_search.gone()
                    viewModel.lookForTheMatch(queryKeywordTeam)
                }
                return true
            }

        })
        menu.findItem(R.id.action_search_view)?.expandActionView()
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showResponseSearch(it: ResponseTimeMatch?) {
        Log.e("testObserve1",""+ it)
        hideErrorMessageSearch()
        searchListMatchOfTeam.adapter = MatchAdapter(it?.events,object :MatchAdapter.onClickItem{
            override fun matchClick(item: EventsTime?) {
                startActivity<MatchDetailActivity>(
                    getString(R.string.detail_match) to item
                )
            }
        })
    }

}
