package com.example.socceralmanac.dbRoom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.socceralmanac.models.match_time.EventsTime

@Dao
interface SubscriberMatchDAO {

    @Insert
    suspend fun insertMatchSubscriber(subscriber: EventsTime): Long

    @Update
    suspend fun updateMatchSubscriber(subscriber: EventsTime):Int

    @Delete
    suspend fun deleteMatchSubscriber(subscriber: EventsTime): Int

    @Query("DELETE FROM subscriber_data_match")
    suspend fun deleteAllMatch():Int

    @Query("SELECT * FROM subscriber_data_match")
    fun getAllMatchSubscribers(): LiveData<List<EventsTime>>
}