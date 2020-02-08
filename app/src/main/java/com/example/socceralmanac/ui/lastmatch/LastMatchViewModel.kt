package com.example.socceralmanac.ui.lastmatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeagueNew
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.repo.GlobalRepository

class LastMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllSoccerLeague>()
    var responsePreviousMatch = MutableLiveData<ResponseTimeMatch>()

    fun forNameOfLeagueLast(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
        },{
            apiError.value = it;
            isLoading.value = false
        })
    }

    fun forPreviousMatchOfLeague(id:String){
        repo.getMatchEventLastName(id,{
            responsePreviousMatch.value =it
            isLoading.value = false
        },{
            apiError.value = it;
            isLoading.value = false
        })
    }





}
