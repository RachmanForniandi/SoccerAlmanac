package com.example.socceralmanac.ui.detailMatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.detail_match.RootDetailMatch
import com.example.socceralmanac.repo.GlobalRepository

class MatchDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseDetailMatch = MutableLiveData<RootDetailMatch>()

    fun accessDetailMatch(idEvent:String){
        repo.getDetailMatchEvent(idEvent,{
            responseDetailMatch.value =it
            isLoading.value = false
        },{
            apiError.value = it
            isLoading.value = false
        })
    }
}
