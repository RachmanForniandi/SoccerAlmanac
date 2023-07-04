package com.example.socceralmanac.ui.lastMatch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.repo.GlobalRepository

class

LastMatchViewModel : ViewModel() {
    private var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseNameLeague = MutableLiveData<ResponseAllLeague>()
    var responsePreviousMatch = MutableLiveData<ResponseAllEvents>()
    private var responseDetailLeagueLast = MutableLiveData<RootDetailLeague>()
    private val param = HashMap<String, Any>()


    fun forNameOfLeagueLast(s: String) {
        repo.leagueSoccerName(s, {
            responseNameLeague.value = it
            isLoading.value = false
            Log.e("debugNameLeague1", "" + it)
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }

    fun forPreviousDetailOfLeague(idLeague: String) {
        isLoading.value = true
        param["id"] = idLeague
        repo.getDetailInfoLeague(param, {
            responseDetailLeagueLast.value = it
            isLoading.value = false
            Log.e("debugDetailLeagueData", "" + it)
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }

    fun resultDetailLast(): MutableLiveData<RootDetailLeague> {
        return responseDetailLeagueLast
    }

    fun forPreviousMatchOfLeague(idLeague: String) {
        isLoading.value = true
        param["id"] = idLeague
        repo.getMatchEventLastName(param, {
            responsePreviousMatch.value = it
            isLoading.value = false
            Log.e("debugListLastMatch", "" + it)
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }

}
