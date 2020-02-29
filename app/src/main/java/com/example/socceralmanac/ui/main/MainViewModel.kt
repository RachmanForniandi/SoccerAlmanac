package com.example.socceralmanac.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.repo.GlobalRepository

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoadingSearch = MutableLiveData<Boolean>()
    var responseSearchOfMatch = MutableLiveData<ResponseTimeMatch>()
    val context: Context? = null
    val paramQuery = HashMap<String, Any>()

    fun lookForTheMatch(e:String){
        isLoadingSearch.value = true
        paramQuery.put("e", e)
        repo.getSearchOfTeamMatch(paramQuery,{
            responseSearchOfMatch.value =it
            isLoadingSearch.value = false
        },{
            apiError.value = it
            isLoadingSearch.value = false
        })
    }
}
