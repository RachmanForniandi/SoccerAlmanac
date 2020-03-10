package com.example.socceralmanac.models.match_time


import com.google.gson.annotations.SerializedName

data class ResponseTimeMatch(

	@field:SerializedName("events")
	val events: List<EventsTime?>? = null
)