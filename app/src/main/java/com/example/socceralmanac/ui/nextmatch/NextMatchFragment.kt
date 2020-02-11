package com.example.socceralmanac.ui.nextmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
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
import com.example.socceralmanac.utility.hide
import com.example.socceralmanac.utility.show
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.next_match_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class NextMatchFragment : Fragment() {

    companion object {
        fun newInstance() = NextMatchFragment()
    }

    private lateinit var viewModel: NextMatchViewModel
    private var content: ArrayList<String>?=null
    private var selectedItem:String= ""
    private var selectedItemId:String = ""
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
        content = ArrayList<String>()

        leagueObserverForNext()

        swipeRefreshNextMatch.setOnRefreshListener {
            val handler = Handler()
            handler.postDelayed(Runnable {
                swipeRefreshNextMatch.isRefreshing = false
            }, 3000)
        }
    }

    private fun leagueObserverForNext() {
        viewModel.responseNameLeague.observe(viewLifecycleOwner, Observer { showNameLeague(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoadingNextMatch(it) })
        viewModel.responseNextMatch.observe(viewLifecycleOwner, Observer {showListOfNextMatch(it)})
    }

    private fun showLoadingNextMatch(it: Boolean?) {
        if (it?:false){
            pgNext.show()
        }else{
            pgNext.hide()
        }
    }


    private fun showNameLeague(it: ResponseAllLeague?) {
        for (i in it?.leagues?.indices ?: ArrayList<String>()){
            content?.add(it?.leagues?.get(i as Int)?.strLeague.toString())
            idLeague.add(it?.leagues?.get(i as Int)?.idLeague.toString())
        }
        val spinnerNext = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,content)
        spinnerNext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_next.adapter = spinnerNext

        spinner_next.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItemId = idLeague[position]
                Toast.makeText( activity,"Kode: $selectedItemId",Toast.LENGTH_SHORT).show()
                viewModel.forNextMatchOfLeague(selectedItemId)
                //Toast.makeText( activity,"Kode: $selectedItemId, Desc: $selectedItem", Toast.LENGTH_SHORT).show()
                //showListOfNextMatch(selectedItemId)
            }
        }
    }

    /*private fun showListOfNextMatch(selectedItemId: String) {

    }*/
    private fun showListOfNextMatch(it: ResponseTimeMatch?) {
        listOfNextMatch.adapter = MatchAdapter(it?.events,object : MatchAdapter.onClickItem{
            override fun matchClick(item: EventsTime?) {
                startActivity<MatchDetailActivity>(
                    "idEvent" to item?.idEvent
                )
            }

        })
    }

}
