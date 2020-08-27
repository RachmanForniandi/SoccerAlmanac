package com.example.socceralmanac.ui.detailMatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.socceralmanac.R
import com.example.socceralmanac.dbRoom.SubscriberMatchDatabase
import com.example.socceralmanac.dbRoom.SubscriberViewModel
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.repo.LocalRepository
import com.example.socceralmanac.utility.getStringDate
import com.example.socceralmanac.utility.getStringTime
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.match_detail_fragment.*
import org.jetbrains.anko.support.v4.toast

class MatchDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDetailFragment()
    }

    private lateinit var viewModelDetail: MatchDetailViewModel
    private lateinit var subscriberViewModel: SubscriberViewModel
    private var idTeamHome: String = ""
    private var idTeamAway: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.match_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelDetail = ViewModelProvider(this).get(MatchDetailViewModel::class.java)

        val dataItem = activity?.intent?.getSerializableExtra("detailMatch") as? EventsTime

        Log.e("debugBundleDetail", "" + dataItem)
        val dao = SubscriberMatchDatabase.getInstance(activity)?.subscriberMatchDAO
        val localRepo = dao?.let { LocalRepository(it) }
        subscriberViewModel = ViewModelProvider(this).get(SubscriberViewModel::class.java)

        idTeamHome = dataItem?.idHomeTeam.toString()
        idTeamAway = dataItem?.idAwayTeam.toString()

        //Toast.makeText(context, "id_1: $idTeamHome, id_2: $idTeamAway", Toast.LENGTH_SHORT).show()

        val detailDateEvent: String = dataItem?.dateEvent?.let { getStringDate(it) } ?: "-"
        val detailTimeEvent: String = dataItem?.strTime?.let { getStringTime(it) } ?: "-:-"


        date_match.text = String.format("%s | %s", detailDateEvent, detailTimeEvent)

        val homeScore = dataItem?.intHomeScore as CharSequence? ?: "-"
        val awayScore = dataItem?.intAwayScore as CharSequence? ?: "-"

        home_yellow_card.text = dataItem?.strHomeYellowCards
        away_yellow_card.text = dataItem?.strAwayYellowCards

        home_red_card.text = dataItem?.strHomeRedCards
        away_red_card.text = dataItem?.strAwayRedCards

        txtHomeTeam.text = dataItem?.strHomeTeam
        txtAwayTeam.text = dataItem?.strAwayTeam

        txtScore.text = String.format("%s - %s", homeScore, awayScore)
        home_goals.text = dataItem?.strHomeGoalDetails
        away_goals.text = dataItem?.strAwayGoalDetails


        player_home_goal_keeper.text = dataItem?.strHomeLineupGoalkeeper
        player_away_goal_keeper.text = dataItem?.strAwayLineupGoalkeeper

        player_home_defense.text = dataItem?.strHomeLineupDefense
        player_away_defense.text = dataItem?.strAwayLineupDefense

        player_home_midfield.text = dataItem?.strHomeLineupMidfield
        player_away_midfield.text = dataItem?.strAwayLineupMidfield

        player_home_forward.text = dataItem?.strHomeLineupForward
        player_away_forward.text = dataItem?.strAwayLineupForward

        player_home_substitutes.text = dataItem?.strHomeLineupSubstitutes
        player_away_substitutes.text = dataItem?.strAwayLineupSubstitutes

        viewModelDetail.accessBadgeHome(idTeamHome)

        viewModelDetail.accessBadgeAway(idTeamAway)

        getResultBadge()

        detailMatchObserver()
    }

    private fun detailMatchObserver() {
        viewModelDetail.isLoading.observe(viewLifecycleOwner, Observer { showLoadingDetailMatch(it) })
        viewModelDetail.apiError.observe(viewLifecycleOwner, Observer { showErrorTeamBadge(it) })
    }

    private fun showErrorTeamBadge(it: Throwable?) {
        toast(it?.message ?: "")
    }

    private fun getResultBadge() {
        viewModelDetail.resultHomeBadge().observe(viewLifecycleOwner, Observer { t ->
            t?.let {
                //Toast.makeText(activity, "aa: $it", Toast.LENGTH_SHORT).show()
                parseLookupTeamHomeResponse(it)
            }
        })

        viewModelDetail.resultAwayBadge().observe(viewLifecycleOwner, Observer { t ->
            t?.let {
                //Toast.makeText(activity, "aa: $it", Toast.LENGTH_SHORT).show()
                parseLookupTeamAwayResponse(it)
            }
        })
    }


    private fun showLoadingDetailMatch(it: Boolean?) {
        if (it == true) {
            progress_detail_circular.show()
        } else {
            progress_detail_circular.hide()
        }
    }

    private fun parseLookupTeamHomeResponse(responseLookUpTeam: ResponseLookUpTeam) {
        val team = responseLookUpTeam.teams
        if (team != null) {
            for (dataTeam in team) {
                val strTeamBadge = dataTeam?.strTeamBadge
                venueMatch.text = dataTeam?.strStadium
                //Toast.makeText(activity, "strTeamBadge: $strTeamBadge", Toast.LENGTH_SHORT).show()
                Glide.with(imgTeamHome)
                    .load(strTeamBadge)
                    .placeholder(R.drawable.soccer_badge)
                    .error(R.drawable.soccer_badge)
                    .into(imgTeamHome)
            }
        }
    }

    private fun parseLookupTeamAwayResponse(responseLookUpTeam: ResponseLookUpTeam) {
        val team = responseLookUpTeam.teams
        if (team != null) {
            for (dataTeam in team) {
                val strTeamBadge = dataTeam?.strTeamBadge
                //Toast.makeText(activity, "strTeamBadge: $strTeamBadge", Toast.LENGTH_SHORT).show()
                Glide.with(imgTeamAway)
                    .load(strTeamBadge)
                    .placeholder(R.drawable.soccer_badge)
                    .error(R.drawable.soccer_badge)
                    .into(imgTeamAway)
            }
        }
    }


}
