package com.example.socceralmanac.dbRoom

import android.app.Application
import androidx.lifecycle.*
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.repo.LocalRepository
import com.example.socceralmanac.utility.EventHandler
import kotlinx.coroutines.launch


class SubscriberViewModel(application: Application) : AndroidViewModel(application){

    private var localRepository = LocalRepository(application)
    private var matches:LiveData<List<EventsTime>>? = localRepository.getMatchData()

    fun insertData(events: EventsTime){
        localRepository.insertFavMatch(events)
    }

    fun getDataMatches():LiveData<List<EventsTime>>?{
        return matches
    }

    fun deleteDataMatches(events: EventsTime){
        localRepository.deleteFavMatch(events)
    }



    /*val localSubscriber = localRepo.getMatchData()
    private var isInsertOrDelete = false
    private lateinit var subscriberLocal:EventsTime

    private val responseMessage = MutableLiveData<EventHandler<String>>()
    val message : LiveData<EventHandler<String>>
    get() = responseMessage*/

    /*fun favoriteMatchInserted(events: EventsTime) = viewModelScope.launch{
        val noOfRows =localRepo.insertFavMatch(events)
        if (noOfRows > -1){
            responseMessage.value=EventHandler("Match Favorited Inserted Successfully $noOfRows")
        }else{
            responseMessage.value=EventHandler("Error Occured")
        }

    }

    fun favoriteMatchUpdated(events: EventsTime) = viewModelScope.launch{
        val noOfRows =localRepo.updateFavMatch(events)
        if (noOfRows>0){
            responseMessage.value = EventHandler("$noOfRows Updated Successfully")
        }else{
            responseMessage.value=EventHandler("Error Occured")
        }
    }

    fun clearAllOrDelete(){
        if (isInsertOrDelete){
            favoriteMatchDeleted(subscriberLocal)
        }else{
            clearAll()
        }
    }

    fun clearAll()= viewModelScope.launch {
        val noOfRows = localRepo.deleteAllFavMatch()
        if (noOfRows > 0) {
            responseMessage.value = EventHandler("$noOfRows Clear all data Successfully")
        } else{
            responseMessage.value = EventHandler("Error Occured")
        }
    }

    private fun favoriteMatchDeleted(subscriberLocal: EventsTime) = viewModelScope.launch {
        val noOfRows = localRepo.deleteFavMatch(subscriberLocal)
        if (noOfRows> 0) {
            responseMessage.value = EventHandler("$noOfRows Deleted Successfully")
        } else{
            responseMessage.value = EventHandler("Error Occured")
        }
    }

*/
}