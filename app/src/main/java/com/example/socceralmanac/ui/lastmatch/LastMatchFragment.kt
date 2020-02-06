package com.example.socceralmanac.ui.lastmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.socceralmanac.R
import com.example.socceralmanac.models.league_soccer.CountrysItem
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import kotlinx.android.synthetic.main.last_match_fragment.*

class LastMatchFragment : Fragment() {

    companion object {
        fun newInstance() = LastMatchFragment()
    }

    private lateinit var viewModel: LastMatchViewModel
    private lateinit var data: CountrysItem
    private var content: ArrayList<String>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.last_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LastMatchViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.forNameOfLeagueLast("Soccer")
        content = ArrayList<String>()
        leagueObserver()
    }

    private fun leagueObserver() {
        viewModel.responseNameLeague.observe(this, Observer { showNameLeague(it) })
    }

    private fun showNameLeague(it: ResponseAllSoccerLeague?) {
        for (i in it?.countrys?.indices ?: ArrayList<String>()){
            //content?.add(it?.countrys?.get(i as Int)?.idLeague.toString()+"-"+it?.countrys?.get(i as Int)?.strLeague.toString())
            content?.add(it?.countrys?.get(i as Int)?.strLeague.toString())
        }

        val spinnerLast = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,content)
        spinnerLast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_last.adapter = spinnerLast

        spinner_last.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()

            }
        }
    }


}
