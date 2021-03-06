package com.example.socceralmanac.models.match_time


import com.example.socceralmanac.models.search.EventItem
import com.google.gson.annotations.SerializedName

data class ResponseAllEvents(

	@field:SerializedName("events")
	val events: List<EventsTime>? = null,

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
)