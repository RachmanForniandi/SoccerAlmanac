package com.example.socceralmanac.ui.searchmatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.socceralmanac.R
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.models.search.EventItem
import com.example.socceralmanac.ui.detailMatch.MatchDetailViewModel
import com.example.socceralmanac.utility.getStringDate
import com.example.socceralmanac.utility.getStringTime
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.match_detail_fragment.*
import org.jetbrains.anko.toast

class SearchMatchDetailActivity : AppCompatActivity() {

    private lateinit var viewModelDetail: MatchDetailViewModel
    private var idTeamHome: String = ""
    private var idTeamAway: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match_detail)

        viewModelDetail = ViewModelProviders.of(this).get(MatchDetailViewModel::class.java)

        val dataItem = intent?.getSerializableExtra("searchMatch") as? EventItem
        idTeamHome = dataItem?.idHomeTeam.toString()
        idTeamAway = dataItem?.idAwayTeam.toString()

        //Toast.makeText(context, "id_1: $idTeamHome, id_2: $idTeamAway", Toast.LENGTH_SHORT).show()

        val detailDateEvent: String = dataItem?.dateEvent?.let { getStringDate(it) } ?: "-"
        val detailTimeEvent: String = dataItem?.strTime?.let { getStringTime(it) } ?: "-:-"


        date_match.text = "$detailDateEvent | $detailTimeEvent"

        val homeScore = dataItem?.intHomeScore?.let { it } as CharSequence? ?: "-"
        val awayScore = dataItem?.intAwayScore?.let { it } as CharSequence? ?: "-"

        home_yellow_card.text = dataItem?.strHomeYellowCards as CharSequence?
        away_yellow_card.text = dataItem?.strAwayYellowCards as CharSequence?

        home_red_card.text = dataItem?.strHomeRedCards as CharSequence?
        away_red_card.text = dataItem?.strAwayRedCards as CharSequence?

        txtHomeTeam.text = dataItem?.strHomeTeam
        txtAwayTeam.text = dataItem?.strAwayTeam

        txtScore.text = "$homeScore " + "-" + " $awayScore"
        home_goals.text = dataItem?.strHomeGoalDetails as CharSequence?
        away_goals.text = dataItem?.strAwayGoalDetails as CharSequence?


        player_home_goal_keeper.text = dataItem?.strHomeLineupGoalkeeper as CharSequence?
        player_away_goal_keeper.text = dataItem?.strAwayLineupGoalkeeper as CharSequence?

        player_home_defense.text = dataItem?.strHomeLineupDefense as CharSequence?
        player_away_defense.text = dataItem?.strAwayLineupDefense as CharSequence?

        player_home_midfield.text = dataItem?.strHomeLineupMidfield as CharSequence?
        player_away_midfield.text = dataItem?.strAwayLineupMidfield as CharSequence?

        player_home_forward.text = dataItem?.strHomeLineupForward as CharSequence?
        player_away_forward.text = dataItem?.strAwayLineupForward as CharSequence?

        player_home_substitutes.text = dataItem?.strHomeLineupSubstitutes as CharSequence?
        player_away_substitutes.text = dataItem?.strAwayLineupSubstitutes as CharSequence?

        viewModelDetail.accessBadgeHome(idTeamHome)

        viewModelDetail.accessBadgeAway(idTeamAway)

        getResultBadge()


        detailMatchObserver()
    }

    private fun detailMatchObserver() {
        viewModelDetail.isLoading.observe(this, Observer { showLoadingDetailMatch(it) })
        viewModelDetail.apiError.observe(this, Observer { showErrorTeamBadge(it) })
        //viewModelDetail.responseAwayBadge.observe(viewLifecycleOwner, Observer{showBadgeTeam(homeBadge, awayBadge)} )
    }

    private fun showErrorTeamBadge(it: Throwable?) {
        toast(it?.message ?: "")
    }

    fun getResultBadge() {
        viewModelDetail.resultHomeBadge().observe(this, Observer { t ->
            t?.let {
                //Toast.makeText(activity, "aa: $it", Toast.LENGTH_SHORT).show()
                parseLookupTeamHomeResponse(it)
            }
        })

        viewModelDetail.resultAwayBadge().observe(this, Observer { t ->
            t?.let {
                //Toast.makeText(activity, "aa: $it", Toast.LENGTH_SHORT).show()
                parseLookupTeamAwayResponse(it)
            }
        })
    }


    private fun showLoadingDetailMatch(it: Boolean?) {
        if (it ?: false) {
            progress_detail_circular.show()
        } else {
            progress_detail_circular.hide()
        }
    }

    fun parseLookupTeamHomeResponse(responseLookUpTeam: ResponseLookUpTeam) {
        val team = responseLookUpTeam.teams
        for (dataTeam in team!!) {
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

    fun parseLookupTeamAwayResponse(responseLookUpTeam: ResponseLookUpTeam) {
        val team = responseLookUpTeam.teams
        for (dataTeam in team!!) {
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
