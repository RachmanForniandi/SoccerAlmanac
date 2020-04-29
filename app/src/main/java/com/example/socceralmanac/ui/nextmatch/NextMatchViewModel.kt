package com.example.socceralmanac.ui.nextmatch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.repo.GlobalRepository

class NextMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllLeague>()
    var responseNextMatch = MutableLiveData<ResponseAllEvents>()
    var responseDetailLeagueNext = MutableLiveData<RootDetailLeague>()
    val param = HashMap<String, Any>()


    fun forNameOfLeagueNext(s:String){
        repo.leagueSoccerName(s,{
            responseNameLeague.value =it
            isLoading.value = false
            Log.e("debugNameLeague2",""+ it)
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun forNextDetailOfLeague(idLeague:String){
        isLoading.value = true
        param.put("id", idLeague)
        repo.getDetailInfoLeague(param,{
            responseDetailLeagueNext.value =it
            isLoading.value = false
            Log.e("debugDetailLeagueData",""+ it)
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun resultDetailNext(): MutableLiveData<RootDetailLeague>{
        return responseDetailLeagueNext
    }

    fun forNextMatchOfLeague(idLeague:String){
        isLoading.value = true
        param.put("id", idLeague)
        repo.getMatchEventNextName(param,{
            responseNextMatch.value =it
            isLoading.value = false
            Log.e("debugListNextMatch",""+ it)
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }
}
