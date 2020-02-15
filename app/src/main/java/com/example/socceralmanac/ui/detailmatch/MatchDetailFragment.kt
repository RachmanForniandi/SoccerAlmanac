package com.example.socceralmanac.ui.detailmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.socceralmanac.R
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.utility.getStringDate
import com.example.socceralmanac.utility.getStringTime
import kotlinx.android.synthetic.main.match_detail_fragment.*

class MatchDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDetailFragment()
    }

    private lateinit var viewModel: MatchDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchDetailViewModel::class.java)
        // TODO: Use the ViewModel

        val dataItem = arguments?.getSerializable("detailMatch")as? EventsTime

        val detailDateEvent: String = dataItem?.dateEvent?.let { getStringDate(it) }?: "-"
        val detailTimeEvent:String = dataItem?.strTime?.let { getStringTime(it) } ?: "-:-"

        date_match.text = "$detailDateEvent | $detailTimeEvent"

        val homeScore = dataItem?.intHomeScore?.let { it } as CharSequence? ?: "-"
        val awayScore = dataItem?.intHomeScore?.let { it } as CharSequence? ?: "-"

        txtHomeTeam.text = dataItem?.strHomeTeam
        txtAwayTeam.text = dataItem?.strAwayTeam

        txtScore.text = "$homeScore " +"-"+ " $awayScore"
        home_goals.text = dataItem?.strHomeGoalDetails
        away_goals.text = dataItem?.strAwayGoalDetails

        /*home_shots.text = dataItem?.intHomeShots as CharSequence
        away_shots.text = dataItem?.intAwayShots as CharSequence*/

        player_home_goal_keeper.text = dataItem?.strHomeLineupGoalkeeper
        player_away_goal_keeper.text = dataItem?.strAwayLineupGoalkeeper

        player_home_midfield.text = dataItem?.strHomeLineupMidfield
        player_away_midfield.text = dataItem?.strAwayLineupMidfield

        player_home_forward.text = dataItem?.strHomeLineupForward
        player_away_forward.text = dataItem?.strAwayLineupForward

        player_home_substitutes.text = dataItem?.strHomeLineupSubstitutes
        player_away_substitutes.text = dataItem?.strAwayLineupSubstitutes

    }

}
