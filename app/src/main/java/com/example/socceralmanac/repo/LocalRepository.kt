package com.example.socceralmanac.repo

import com.example.socceralmanac.dbRoom.SubscriberMatchDAO
import com.example.socceralmanac.models.match_time.EventsTime

class LocalRepository(private val dao: SubscriberMatchDAO) {

    val favMatchSubscribers = dao.getAllMatchSubscribers()

    suspend fun insertFavMatch(subscriber: EventsTime):Long{
        return dao.insertMatchSubscriber(subscriber)
    }

    suspend fun updateFavMatch(subscriber: EventsTime):Int{
        return dao.updateMatchSubscriber(subscriber)
    }

    suspend fun deleteFavMatch(subscriber: EventsTime):Int{
        return dao.deleteMatchSubscriber(subscriber)
    }

    suspend fun deleteAllFavMatch():Int{
        return dao.deleteAllMatch()
    }
}