package com.example.socceralmanac.ui.nextmatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import com.example.socceralmanac.repo.GlobalRepository

class NextMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllSoccerLeague>()

    fun forNameOfLeagueNext(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
        },{
            apiError.value = it;
            isLoading.value = false
        })
    }
}
