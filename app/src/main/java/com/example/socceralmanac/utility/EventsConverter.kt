package com.example.socceralmanac.utility

import androidx.room.TypeConverter
import com.example.socceralmanac.models.match_time.EventsTime

class EventsConverter {

    @TypeConverter
    fun fromEvents(events: String?): EventsTime? {
        return events?.let { EventsTime(it) }
    }

    @TypeConverter
    fun toEvents(events: String):EventsTime{
        return EventsTime(events,events)
    }
}