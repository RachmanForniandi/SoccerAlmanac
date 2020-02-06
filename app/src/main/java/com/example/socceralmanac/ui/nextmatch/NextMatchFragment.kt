package com.example.socceralmanac.ui.nextmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.socceralmanac.R
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.next_match_fragment.*

class NextMatchFragment : Fragment() {

    companion object {
        fun newInstance() = NextMatchFragment()
    }

    private lateinit var viewModel: NextMatchViewModel
    private var content: ArrayList<String>?=null

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
        content = ArrayList<String>()
        leagueObserverForNext()
    }

    private fun leagueObserverForNext() {
        viewModel.responseNameLeague.observe(this, Observer { showNameLeague(it) })
    }


    private fun showNameLeague(it: ResponseAllSoccerLeague?) {
        for (i in it?.countrys?.indices ?: ArrayList<String>()){
            content?.add(it?.countrys?.get(i as Int)?.strLeague.toString())
        }

        val spinnerNext = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,content)
        spinnerNext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_next.adapter = spinnerNext

    }



}
