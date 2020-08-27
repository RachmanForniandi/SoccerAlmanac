package com.example.socceralmanac.dbRoom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.repo.LocalRepository
import com.example.socceralmanac.utility.EventHandler
import kotlinx.coroutines.launch


class SubscriberViewModel(private val localRepo: LocalRepository) : ViewModel(){

    val localSubscriber = localRepo.favMatchSubscribers
    private var isInsertOrDelete = false
    private lateinit var subscriberLocal:EventsTime

    private val responseMessage = MutableLiveData<EventHandler<String>>()
    val message : LiveData<EventHandler<String>>
    get() = responseMessage

    fun favoriteMatchInserted(events: EventsTime) = viewModelScope.launch{
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


}