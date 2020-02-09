package com.example.socceralmanac.ui.lastmatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.repo.GlobalRepository

class LastMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllLeague>()
    var responsePreviousMatch = MutableLiveData<ResponseTimeMatch>()

    //utk nama liga di spinner
    fun forNameOfLeagueLast(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    //utk list match previous
    fun forPreviousMatchOfLeague(idLeague:String){
        repo.getMatchEventLastName(idLeague,{
            responsePreviousMatch.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

}
