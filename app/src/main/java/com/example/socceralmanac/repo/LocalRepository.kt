package com.example.socceralmanac.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.socceralmanac.dbRoom.SubscriberMatchDAO
import com.example.socceralmanac.dbRoom.SubscriberMatchDatabase
import com.example.socceralmanac.models.match_time.EventsTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LocalRepository(application: Application) {

    private val matchDao: SubscriberMatchDAO?

    private var matches:LiveData<List<EventsTime>>?= null

    init {
        val dbMatchDAO =  SubscriberMatchDatabase.getInstance(application.applicationContext)
        matchDao = dbMatchDAO?.subscriberMatchDAO()
        matches = matchDao?.getMatches()

    }

    fun getMatchData():LiveData<List<EventsTime>>?{
        return matches
    }

    fun insertFavMatch(events: EventsTime)= runBlocking {
        this.launch (Dispatchers.IO){
            matchDao?.insertMatchSubscriber(events)
        }
    }

    fun deleteFavMatch(events: EventsTime)= runBlocking {
        this.launch(Dispatchers.IO) {
            matchDao?.deleteMatchSubscriber(events)
        }
    }


}