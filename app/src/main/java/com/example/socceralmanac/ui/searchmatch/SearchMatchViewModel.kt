package com.example.socceralmanac.ui.searchmatch

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socceralmanac.models.search.ResponseSearch
import com.example.socceralmanac.repo.GlobalRepository

class SearchMatchViewModel : ViewModel() {
    var repo = GlobalRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoadingSearch = MutableLiveData<Boolean>()
    var responseSearchOfMatch = MutableLiveData<ResponseSearch>()
    val context: Context? = null
    val paramQuery = HashMap<String, Any>()

    fun lookForTheMatch(e:String){
        isLoadingSearch.value = true
        paramQuery.put("e", e)
        repo.getSearchOfTeamMatch(paramQuery,{
            responseSearchOfMatch.value =it
            isLoadingSearch.value = false
            Log.e("debugVMSearch",""+ it)
        },{
            apiError.value = it
            isLoadingSearch.value = false
        })
    }

    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }


}
