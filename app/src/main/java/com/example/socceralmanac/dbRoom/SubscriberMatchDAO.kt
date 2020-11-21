package com.example.socceralmanac.dbRoom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.socceralmanac.models.match_time.EventsTime

@Dao
interface SubscriberMatchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchSubscriber(subscriber: EventsTime)

    @Delete
    suspend fun deleteMatchSubscriber(subscriber: EventsTime)

    @Query("SELECT * FROM subscriber_data_match")
    fun getMatches(): LiveData<List<EventsTime>>
}