package com.example.socceralmanac.ui.lastmatch

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.repo.GlobalRepository

class LastMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllLeague>()
    var responsePreviousMatch = MutableLiveData<ResponseAllEvents>()
    val context: Context? = null
    val param = HashMap<String, Any>()


    //utk nama liga di spinner
    fun forNameOfLeagueLast(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
            Log.e("debugNameLeague1",""+ it)
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    //utk list match previous
    fun forPreviousMatchOfLeague(idLeague:String){
        val eventSearch: MutableList<EventsTime> = mutableListOf()
        isLoading.value = true
        param.put("id", idLeague)
        repo.getMatchEventLastName(param,{
            responsePreviousMatch.value =it
            isLoading.value = false
            Log.e("debugListLastMatch",""+ it)
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun getFilteredPreviousMatch():MutableLiveData<ResponseAllEvents>{
        return responsePreviousMatch
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }

}
