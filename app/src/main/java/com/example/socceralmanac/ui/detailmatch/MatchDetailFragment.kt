package com.example.socceralmanac.ui.detailMatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.socceralmanac.R
import com.example.socceralmanac.models.lookup_team.TeamsItem
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.utility.getStringDate
import com.example.socceralmanac.utility.getStringTime
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.match_detail_fragment.*

class MatchDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDetailFragment()
    }

    private lateinit var viewModelDetail: MatchDetailViewModel
    private var idTeamHome:String= ""
    private var idTeamAway:String= ""
    val homeBadge: MutableList<TeamsItem> = mutableListOf()
    val awayBadge: MutableList<TeamsItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.match_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelDetail = ViewModelProviders.of(this).get(MatchDetailViewModel::class.java)

        val dataItem = activity?.intent?.getSerializableExtra("detailMatch") as? EventsTime
        Log.e("debugBundleDetail",""+dataItem);

       /* idTeamHome = activity?.intent?.getStringExtra("idTeamHome").toString()

        idTeamAway = activity?.intent?.getStringExtra("idTeamAway").toString()*/

        /*viewModelDetail.accessBadgeHome(idTeamHome)
        viewModelDetail.accessBadgeAway(idTeamAway)*/
        
        //activity?.intent?.getStringExtra("")

        /*val detailDateEvent: String = activity?.intent?.getStringExtra("dateEvent").let { getStringDate(it.toString()) }?: "-"
        val detailTimeEvent:String = activity?.intent?.getStringExtra("strTime").let { getStringTime(it.toString()) } ?: "-:-"
        date_match.text = "$detailDateEvent | $detailTimeEvent"

        val homeScore = activity?.intent?.getStringExtra("intHomeScore")?.let { it } as CharSequence? ?: "-"
        val awayScore = activity?.intent?.getStringExtra("intAwayScore")?.let { it } as CharSequence? ?: "-"

        home_yellow_card.text =activity?.intent?.getStringExtra("strHomeYellowCards")?.let { it } as CharSequence? ?: "-"
        away_yellow_card.text =activity?.intent?.getStringExtra("strAwayYellowCards")?.let { it } as CharSequence? ?: "-"

        home_red_card.text =activity?.intent?.getStringExtra("strHomeRedCards")?.let { it } as CharSequence? ?: "-"
        away_red_card.text =activity?.intent?.getStringExtra("strAwayRedCards")?.let { it } as CharSequence? ?: "-"

        txtHomeTeam.text = activity?.intent?.getStringExtra("strHomeTeam")
        txtAwayTeam.text = activity?.intent?.getStringExtra("strAwayTeam")

        txtScore.text = "$homeScore " +"-"+ " $awayScore"
        home_goals.text = activity?.intent?.getStringExtra("strHomeGoalDetails")
        away_goals.text = activity?.intent?.getStringExtra("strAwayGoalDetails")


        player_home_goal_keeper.text = activity?.intent?.getStringExtra("strHomeLineupGoalkeeper")
        player_away_goal_keeper.text = activity?.intent?.getStringExtra("strAwayLineupGoalkeeper")

        player_home_defense.text = activity?.intent?.getStringExtra("strHomeLineupDefense")
        player_away_defense.text = activity?.intent?.getStringExtra("strAwayLineupDefense")

        player_home_midfield.text = activity?.intent?.getStringExtra("strHomeLineupMidfield")
        player_away_midfield.text = activity?.intent?.getStringExtra("strAwayLineupMidfield")

        player_home_forward.text = activity?.intent?.getStringExtra("strHomeLineupForward")
        player_away_forward.text = activity?.intent?.getStringExtra("strAwayLineupForward")

        player_home_substitutes.text = activity?.intent?.getStringExtra("strHomeLineupSubstitutes")
        player_away_substitutes.text = activity?.intent?.getStringExtra("strAwayLineupSubstitutes")*/

        idTeamHome = dataItem?.idHomeTeam.toString()
        idTeamAway = dataItem?.idAwayTeam.toString()


        val detailDateEvent: String = dataItem?.dateEvent?.let { getStringDate(it) }?: "-"
        val detailTimeEvent:String = dataItem?.strTime?.let { getStringTime(it) } ?: "-:-"

        date_match.text = "$detailDateEvent | $detailTimeEvent"

        val homeScore = dataItem?.intHomeScore?.let { it } as CharSequence? ?: "-"
        val awayScore = dataItem?.intHomeScore?.let { it } as CharSequence? ?: "-"

        home_yellow_card.text = dataItem?.strHomeYellowCards
        away_yellow_card.text = dataItem?.strAwayYellowCards

        home_red_card.text =dataItem?.strHomeRedCards
        away_red_card.text =dataItem?.strAwayRedCards

        txtHomeTeam.text = dataItem?.strHomeTeam
        txtAwayTeam.text = dataItem?.strAwayTeam

        txtScore.text = "$homeScore " +"-"+ " $awayScore"
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


        detailMatchObserver()
    }

    private fun detailMatchObserver(){
        viewModelDetail.isLoading.observe(viewLifecycleOwner, Observer{showLoadingDetailMatch(it)})
        viewModelDetail.responseAwayBadge.observe(viewLifecycleOwner, Observer{showBadgeTeam(homeBadge, awayBadge)} )
    }

    private fun showBadgeTeam(homeBadge: List<TeamsItem>, awayBadge: List<TeamsItem>) {
        viewModelDetail.accessBadgeHome(idTeamHome)
        //viewModelDetail.accessBadgeAway(idTeamAway)
        Glide.with(imgTeamHome)
            .load(homeBadge[0].strTeamBadge)
            .placeholder(R.drawable.soccer_badge)
            .error(R.drawable.soccer_badge)
            .into(imgTeamHome)

        viewModelDetail.accessBadgeAway(idTeamAway)
        Glide.with(imgTeamAway)
            .load(awayBadge[0].strTeamBadge)
            .placeholder(R.drawable.soccer_badge)
            .error(R.drawable.soccer_badge)
            .into(imgTeamAway)
    }

    /*private fun showBadgeHomeTeam(it: ResponseLookUpTeam?) {

    }*/

    private fun showLoadingDetailMatch(it: Boolean?) {
        if (it?:false){
            progress_detail_circular.show()
        }else{
            progress_detail_circular.hide()
        }
    }

}