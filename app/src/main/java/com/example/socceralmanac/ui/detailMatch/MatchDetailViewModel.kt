package com.example.socceralmanac.ui.detailMatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.repo.GlobalRepository

class MatchDetailViewModel : ViewModel() {
    private var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    private var responseHomeBadge = MutableLiveData<ResponseLookUpTeam>()
    private var responseAwayBadge = MutableLiveData<ResponseLookUpTeam>()
    private val paramIdteamHome = HashMap<String, Any>()
    private val paramIdteamAway = HashMap<String, Any>()

    fun accessBadgeHome(idHomeTeam: String) {
        isLoading.value = true
        paramIdteamHome["id"] = idHomeTeam
        repo.getBadgeLogoTeam(paramIdteamHome, {
            //Toast.makeText(activity, "idBadge "+ it.toString(), Toast.LENGTH_SHORT).show()
            println("idBadge1:  " + it.toString())
            responseHomeBadge.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }

    fun accessBadgeAway(idAwayTeam: String) {
        isLoading.value = true
        paramIdteamAway["id"] = idAwayTeam
        repo.getBadgeLogoTeam(paramIdteamAway, {
            println("idBadge2:  " + it.toString())
            responseAwayBadge.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }

    fun resultHomeBadge(): MutableLiveData<ResponseLookUpTeam> {
        return responseHomeBadge
    }

    fun resultAwayBadge(): MutableLiveData<ResponseLookUpTeam> {
        return responseAwayBadge
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }
}
