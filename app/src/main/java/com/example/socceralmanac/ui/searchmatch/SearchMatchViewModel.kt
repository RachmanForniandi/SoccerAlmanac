package com.example.socceralmanac.ui.searchmatch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.repo.GlobalRepository

class SearchMatchViewModel : ViewModel() {
    private var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoadingSearch = MutableLiveData<Boolean>()
    var responseSearchOfMatch = MutableLiveData<ResponseAllEvents>()

    private val paramQuery = HashMap<String, Any>()

    fun lookForTheMatch(e: String) {
        isLoadingSearch.value = true
        paramQuery.put("e", e)
        repo.getSearchOfTeamMatch(paramQuery, {
            responseSearchOfMatch.value = it
            isLoadingSearch.value = false
            Log.e("debugVMSearch", "" + it)
        }, {
            apiError.value = it
            isLoadingSearch.value = false
        })
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }


}
