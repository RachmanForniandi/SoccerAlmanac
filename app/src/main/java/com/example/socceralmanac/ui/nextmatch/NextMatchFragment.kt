package com.example.socceralmanac.ui.nextmatch

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.league_soccer.LeaguesItem
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.ui.detailmatch.MatchDetailActivity
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_dialog_detail_league.view.*
import kotlinx.android.synthetic.main.next_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment() {

    companion object {
        fun newInstance() = NextMatchFragment()
    }

    private lateinit var viewModel: NextMatchViewModel
    private var content: ArrayList<String>? = null
    private var selectedItem: String = ""
    private var selectedItemId: String = ""
    private var selectedItemId2: String = ""
    private var idLeague = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.next_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NextMatchViewModel::class.java)
        viewModel.forNameOfLeagueNext("Soccer")
        content = ArrayList<String>()

        leagueObserverForNext()

        getResultDetailLeague()

        swipeRefreshNextMatch.setOnRefreshListener {
            val handler = Handler()
            handler.postDelayed({
                swipeRefreshNextMatch.isRefreshing = false
            }, 3000)
        }

    }

    private fun leagueObserverForNext() {
        viewModel.responseNameLeague.observe(viewLifecycleOwner, Observer { showNameLeague(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoadingNextMatch(it) })
        viewModel.responseNextMatch.observe(
            viewLifecycleOwner,
            Observer { showListOfNextMatch(it) })
        viewModel.apiError.observe(viewLifecycleOwner, Observer { showErrorMatch(it) })

    }

    private fun getResultDetailLeague() {
        viewModel.resultDetailNext().observe(viewLifecycleOwner, Observer { t ->
            t?.let {
                parseForNextDetailInfoLeague(it)
            }
        })
    }

    private fun parseForNextDetailInfoLeague(it: RootDetailLeague) {
        val detail = it.leagues
        if (detail != null) {
            for (detailParam in detail) {
                val linkBannerLeague = detailParam?.strBanner
                val leagueBackground = detailParam?.strPoster
                val leagueName = detailParam?.strLeague
                val leagueBadge = detailParam?.strBadge
                val leagueCountry = detailParam?.strCountry
                val leagueDetailInfo = detailParam?.strDescriptionEN

                Picasso.get()
                    .load(linkBannerLeague)
                    .centerCrop()
                    .fit()
                    .placeholder(R.drawable.blue_banner)
                    .error(R.drawable.blue_banner)
                    .into(img_banner_league_2)
                Log.e("debugBanner", "" + detailParam?.strBanner)

                Picasso.get()
                    .load(leagueBackground)
                    .centerCrop()
                    .fit()
                    .into(background2)
                Log.e("debugPoster", "" + detailParam?.strPoster)

                btn_detail_league.setOnClickListener {
                    val dialogView = LayoutInflater.from(activity)
                        .inflate(R.layout.layout_dialog_detail_league, null)

                    val dialogBuilder = AlertDialog.Builder(activity)
                        .setView(dialogView)

                    val detailDialog = dialogBuilder.show()

                    detailDialog.setCanceledOnTouchOutside(false)

                    Glide.with(dialogView.detail_badge_league)
                        .load(leagueBadge)
                        .placeholder(R.drawable.soccer_badge)
                        .error(R.drawable.soccer_badge)
                        .into(dialogView.detail_badge_league)

                    dialogView.txt_name_league.text = leagueName

                    dialogView.tvCountryLeague.text = leagueCountry

                    dialogView.detail_desc_league.text = leagueDetailInfo


                    dialogView.close_bottom_sheet.setOnClickListener {
                        detailDialog.dismiss()
                    }
                }


            }
        }
    }

    private fun showNameLeague(it: ResponseAllLeague?) {
        val eventLeagueNext: MutableList<LeaguesItem> = mutableListOf()
        it?.leagues.let {
            val sportFiltered: List<LeaguesItem> =
                it?.filter { s -> s?.strSport == "Soccer" } as List<LeaguesItem>
            for (i in sportFiltered.indices) {
                Log.e("testObserveNameLeague2", "" + it)
                content?.add(sportFiltered.get(i).strLeague.toString())
                idLeague.add(sportFiltered.get(i).idLeague.toString())
                eventLeagueNext.addAll(sportFiltered)
            }
        }

        val spinnerNext =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, content)
        spinnerNext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_next.adapter = spinnerNext

        spinner_next.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent?.getItemAtPosition(position).toString()

                selectedItemId = idLeague[position]
                selectedItemId2 = idLeague[position]

                viewModel.forNextMatchOfLeague(selectedItemId)
                viewModel.forNextDetailOfLeague(selectedItemId2)
            }
        }
    }

    private fun showListOfNextMatch(it: ResponseAllEvents?) {
        Log.e("testObserveNextMatch", "" + it)
        val eventList: MutableList<EventsTime> = mutableListOf()
        it?.events.let {
            val filterState: List<EventsTime> = it?.filter { s -> it != null } as List<EventsTime>
            eventList.addAll(filterState)
            listOfNextMatch.adapter = MatchAdapter(eventList, object : MatchAdapter.OnClickItem {

                override fun matchClick(time: EventsTime?) {
                    startActivity<MatchDetailActivity>(
                        "detailMatch" to time
                    )
                }
            })
        }

    }

    private fun showErrorMatch(it: Throwable?) {
        toast(it?.message ?: "")
    }

    private fun showLoadingNextMatch(it: Boolean?) {
        if (it == true) {
            pgNext.show()
        } else {
            pgNext.hide()
        }
    }
}
