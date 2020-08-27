package com.example.socceralmanac.dbRoom

import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.socceralmanac.models.match_time.EventsTime

@Database(entities = [EventsTime::class],version = 1)
abstract class SubscriberMatchDatabase: RoomDatabase() {

    abstract val subscriberMatchDAO:SubscriberMatchDAO

    companion object{
        @Volatile
        private var INSTANCE : SubscriberMatchDatabase? = null
        fun getInstance(context: FragmentActivity?): SubscriberMatchDatabase? {
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    if (context != null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            SubscriberMatchDatabase::class.java,
                            "subscriber_data_match_database"
                        ).build()
                    }
                }
                return instance
            }
        }
    }
}