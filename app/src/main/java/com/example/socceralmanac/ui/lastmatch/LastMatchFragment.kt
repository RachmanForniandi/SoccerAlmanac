package com.example.socceralmanac.ui.lastmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.socceralmanac.MatchDetailActivity
import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.MatchAdapter
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import kotlinx.android.synthetic.main.last_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class LastMatchFragment : Fragment() {

    companion object {
        fun newInstance() = LastMatchFragment()
    }

    private lateinit var viewModel: LastMatchViewModel
    private var content: ArrayList<String>?=null
    private var selectedItem:String= ""
    private var selectedItemId:String = ""
    var idLeague = ArrayList<String>()

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
        viewModel.forPreviousMatchOfLeague(selectedItemId)
        content = ArrayList<String>()
        leagueObserver()
    }

    private fun leagueObserver() {
        viewModel.responseNameLeague.observe(viewLifecycleOwner, Observer { showNameLeague(it) })
        viewModel.responsePreviousMatch.observe(viewLifecycleOwner, Observer { showListOfPreviousMatch(it) })
    }



    private fun showNameLeague(it: ResponseAllLeague?) {
        for (i in it?.leagues?.indices ?: ArrayList<String>()){
            //content?.add(it?.countrys?.get(i as Int)?.idLeague.toString()+"-"+it?.countrys?.get(i as Int)?.strLeague.toString())
            content?.add(it?.leagues?.get(i as Int)?.strLeague.toString())
            idLeague.add(it?.leagues?.get(i as Int)?.idLeague.toString())
        }

        val spinnerLast = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,content)
        spinnerLast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_last.adapter = spinnerLast

        spinner_last.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItemId = idLeague[position]
                Toast.makeText( activity,"Kode: $selectedItemId",Toast.LENGTH_SHORT).show()
                //showListOfPreviousMatch(selectedItemId)
            }
        }
    }

    /*private fun showListOfPreviousMatch(selectedItemId: String) {
        viewModel.responsePreviousMatch(selectedItemId)
    }*/

    private fun showListOfPreviousMatch(it: ResponseTimeMatch?) {

        listOfLastMatch.adapter = MatchAdapter(it?.events,object :MatchAdapter.onClickItem{
            override fun matchClick(item: EventsTime?) {
                startActivity<MatchDetailActivity>(
                    "idEvent" to item?.idEvent
                )
            }

        })
    }




}
