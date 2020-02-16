package com.example.socceralmanac.ui.detailMatch

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
    val paramIdteam = HashMap<String, Any>()

    fun accessBadgeHome(idHomeTeam:String){
        isLoading.value = true
        paramIdteam.put("id", idHomeTeam)
        repo.getBadgeLogoTeam(paramIdteam,{
            responseHomeBadge.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }

    fun accessBadgeAway(idAwayTeam:String){
        isLoading.value = true
        paramIdteam.put("id", idAwayTeam)
        repo.getBadgeLogoTeam(paramIdteam,{
            responseAwayBadge.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }
}
