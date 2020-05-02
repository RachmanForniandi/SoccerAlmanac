package com.example.socceralmanac.ui.searchmatch

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.SearchMatchAdapter
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.models.search.EventItem
import com.example.socceralmanac.utility.*
import kotlinx.android.synthetic.main.search_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class SearchMatchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMatchFragment()
    }

    private lateinit var viewModel: SearchMatchViewModel
    private lateinit var queryKeywordTeam:String
    private var itemSearchMatches: MutableList<EventItem> = mutableListOf()

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
        viewModel = ViewModelProvider(this).get(SearchMatchViewModel::class.java)

        searchObserver()
    }

    private fun searchObserver() {
        viewModel.responseSearchOfMatch.observe(viewLifecycleOwner, Observer {showResponseSearch(it)})
        viewModel.isLoadingSearch.observe(viewLifecycleOwner, Observer {showLoadingSearch(it)})
        viewModel.apiError.observe(viewLifecycleOwner, Observer {showErrorSearch(it)})
    }

    private fun showErrorSearch(it: Throwable?) {
        showTextErrorSearch("Sorry No Result for" + " '$queryKeywordTeam'" +
                "\n $it")
        img_background_search.visible()
        searchListMatchOfTeam.gone()
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
        if (it ?: false)pbSearch.show()
        else pbSearch.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.action_search_view)?.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint =getString(R.string.search_your_team)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryKeywordTeam = query.toString()
                viewModel.lookForTheMatch(queryKeywordTeam)
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                queryKeywordTeam = newText.toString()
                if (queryKeywordTeam.length ==0){
                    itemSearchMatches.clear()
                    searchListMatchOfTeam.adapter?.notifyDataSetChanged()
                }else viewModel.lookForTheMatch(queryKeywordTeam)
                return true
            }
        })
        menu.findItem(R.id.action_search_view)?.expandActionView()
        return super.onCreateOptionsMenu(menu, inflater)
    }


    private fun showResponseSearch(it: ResponseAllEvents?) {
        Log.e("testObserve1",""+ it)
        hideErrorMessageSearch()
        val eventSearchNoted: MutableList<EventItem> = mutableListOf()
        it?.event.let {
            val sportFiltered: List<EventItem> = it?.filter { s -> s?.strSport == "Soccer" } as List<EventItem>
            eventSearchNoted.addAll(sportFiltered)

            searchListMatchOfTeam.adapter = SearchMatchAdapter(eventSearchNoted,object :SearchMatchAdapter.onClickItem{
                override fun searchMatchClick(item: EventItem?) {
                    startActivity<SearchMatchDetailActivity>(
                        "searchMatch" to item
                    )
                }
            })
        }

    }

}


