package com.example.socceralmanac.dbRoom

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.utility.EventsConverter

@Database(entities = [EventsTime::class],exportSchema = false,version = 1)
@TypeConverters(EventsConverter::class)
abstract class SubscriberMatchDatabase: RoomDatabase() {

    abstract fun subscriberMatchDAO():SubscriberMatchDAO

    companion object{
        @Volatile
        private var INSTANCE : SubscriberMatchDatabase? = null
        fun getInstance(context: Context?): SubscriberMatchDatabase? {
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    if (context != null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            SubscriberMatchDatabase::class.java,
                            "subscriber_data_match"
                        ).build()
                    }
                }
                return instance
            }
        }
    }
}