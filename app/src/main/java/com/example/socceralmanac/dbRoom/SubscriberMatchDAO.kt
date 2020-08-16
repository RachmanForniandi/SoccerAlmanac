package com.example.socceralmanac.dbRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.socceralmanac.models.match_time.EventsTime

@Dao
interface SubscriberMatchDAO {

    @Insert
    suspend fun insertMatchSubscriber(subscriber: EventsTime): Long

    @Delete
    suspend fun deleteMatchSubscriber(subscriber: EventsTime): Int

    @Query("DELETE FROM subscriber_data_match")
    suspend fun deleteAllMatch():Int

    @Query("SELECT * FROM subscriber_data_match")
    fun getAllMatchSubscribers(): LiveData<List<EventsTime>>
}