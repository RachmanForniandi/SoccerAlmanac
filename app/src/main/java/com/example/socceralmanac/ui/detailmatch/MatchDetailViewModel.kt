package com.example.socceralmanac.ui.detailMatch

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.repo.GlobalRepository

class MatchDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseHomeBadge = MutableLiveData<ResponseLookUpTeam>()
    var responseAwayBadge = MutableLiveData<ResponseLookUpTeam>()
    val paramIdteamHome = HashMap<String, Any>()
    val paramIdteamAway = HashMap<String, Any>()

    fun accessBadgeHome( idHomeTeam:String){
        isLoading.value = true
        paramIdteamHome.put("id", idHomeTeam)
        repo.getBadgeLogoTeam(paramIdteamHome,{
            //Toast.makeText(activity, "idBadge "+ it.toString(), Toast.LENGTH_SHORT).show()
            println("idBadge1:  "+ it.toString())
            responseHomeBadge.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun accessBadgeAway(idAwayTeam:String){
        isLoading.value = true
        paramIdteamAway.put("id", idAwayTeam)
        repo.getBadgeLogoTeam(paramIdteamAway,{
            println("idBadge2:  "+ it.toString())
            responseAwayBadge.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun resultHomeBadge(): MutableLiveData<ResponseLookUpTeam>{
        return responseHomeBadge
    }

    fun resultAwayBadge(): MutableLiveData<ResponseLookUpTeam>{
        return responseAwayBadge
    }
}
