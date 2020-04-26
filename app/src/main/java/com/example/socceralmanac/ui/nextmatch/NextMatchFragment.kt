package com.example.socceralmanac.ui.nextmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.league_soccer.LeaguesItem
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.ui.detailMatch.MatchDetailActivity
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.next_match_fragment.*
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.wrapContent

class NextMatchFragment : Fragment() {

    companion object {
        fun newInstance() = NextMatchFragment()
    }

    private lateinit var viewModel: NextMatchViewModel
    private var content: ArrayList<String>?=null
    private var selectedItem:String= ""
    private var selectedItemId:String = ""
    private var selectedItemId2:String = ""
    var idLeague = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.next_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
        viewModel.forNameOfLeagueNext("Soccer")
        viewModel.forNextMatchOfLeague(selectedItemId)
        viewModel.forNextDetailOfLeague(selectedItemId2)
        content = ArrayList<String>()

        getResultDetailLeague()

        leagueObserverForNext()

        swipeRefreshNextMatch.setOnRefreshListener {
            val handler = Handler()
            handler.postDelayed(Runnable {
                swipeRefreshNextMatch.isRefreshing = false
            }, 3000)
        }

        getFilteredResultNextMatch()
    }

    private fun getResultDetailLeague() {
        viewModel.resultDetailNext().observe(viewLifecycleOwner, Observer {
            //showDetailInfoLeague(it)
                t ->
            t?.let{
                parseForNextDetailInfoLeague(it)
            }
        })
    }

    private fun parseForNextDetailInfoLeague(it: RootDetailLeague) {
        val detail = it.leagues
        if (detail != null){
            for (detailParam in detail){
                val linkBannerLeague = detailParam?.strBanner
                val leagueBackground = detailParam?.strPoster

                Glide.with(img_banner_league_2)
                    .load(linkBannerLeague)
                    .fitCenter()
                    .override(matchParent, wrapContent)
                    .placeholder(R.drawable.blue_banner)
                    .error(R.drawable.blue_banner)
                    .into(img_banner_league_2)
                Log.e("debugBanner",""+ detailParam?.strBanner)

                Glide.with(background2)
                    .load(leagueBackground)
                    .fitCenter()
                    .override(800,600)
                    .into(background2)
                Log.e("debugPoster",""+ detailParam?.strPoster)
            }
        }
    }

    private fun leagueObserverForNext() {
        viewModel.responseNameLeague.observe(viewLifecycleOwner, Observer { showNameLeague(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoadingNextMatch(it) })
        viewModel.apiError.observe(viewLifecycleOwner, Observer { showErrorMatch(it) })
    }


    private fun showErrorMatch(it: Throwable?) {
        toast(it?.message ?: "")
    }

    private fun showLoadingNextMatch(it: Boolean?) {
        if (it?:false){
            pgNext.show()
        }else{
            pgNext.hide()
        }
    }


    private fun showNameLeague(it: ResponseAllLeague?) {
        val eventLeagueNext: MutableList<LeaguesItem> = mutableListOf()
        it?.leagues.let {
            val sportFiltered: List<LeaguesItem> = it?.filter { s -> s?.strSport == "Soccer" } as List<LeaguesItem>
            for (i in sportFiltered.indices){
                Log.e("testObserveNameLeague2",""+ it)
                content?.add(sportFiltered.get(i).strLeague.toString())
                idLeague.add(sportFiltered.get(i).idLeague.toString())
                eventLeagueNext.addAll(sportFiltered)
            }
        }

        val spinnerNext = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,content)
        spinnerNext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_next.adapter = spinnerNext

        spinner_next.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position).toString()

                selectedItemId = idLeague[position]
                selectedItemId2 = idLeague[position]
                //Toast.makeText( activity,"Kode: $selectedItemId",Toast.LENGTH_SHORT).show()
                viewModel.forNextMatchOfLeague(selectedItemId)
                viewModel.forNextDetailOfLeague(selectedItemId2)
                //Toast.makeText( activity,"Kode: $selectedItemId, Desc: $selectedItem", Toast.LENGTH_SHORT).show()
                //showListOfNextMatch(selectedItemId)
            }
        }
    }



    fun getFilteredResultNextMatch(){
        viewModel.getFilteredNextMatch().observe(viewLifecycleOwner, Observer {
                t ->
            t?.let{
                parseFilteredNextMatch(it)
            }
        })
    }

    fun parseFilteredNextMatch(it: ResponseAllEvents){
        val event = it.events
        if (event != null){
            showListOfNextMatch(it)
        }
    }


    private fun showListOfNextMatch(it: ResponseAllEvents?) {
        Log.e("testObserveNextMatch",""+ it)
        val eventList: MutableList<EventsTime> = mutableListOf()
        it?.events.let {
            val filterState :List<EventsTime> = it?.filter { s -> it != null } as List<EventsTime>
            eventList.addAll(filterState)
            listOfNextMatch.adapter = MatchAdapter(eventList,object : MatchAdapter.onClickItem{

                override fun matchClick(time: EventsTime?) {
                    startActivity<MatchDetailActivity>(
                        "detailMatch" to time
                    )
                }
            })
        }

    }

}
