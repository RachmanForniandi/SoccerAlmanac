package com.example.socceralmanac.models.match_time_2


import com.google.gson.annotations.SerializedName

data class ResponseEvents(

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null
)