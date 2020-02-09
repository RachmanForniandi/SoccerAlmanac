package com.example.socceralmanac.ui.nextmatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.repo.GlobalRepository

class NextMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllLeague>()
    var responseNextMatch = MutableLiveData<ResponseTimeMatch>()

    fun forNameOfLeagueNext(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun forNextMatchOfLeague(idLeague:String){
        repo.getMatchEventNextName(idLeague,{
            responseNextMatch.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }
}
