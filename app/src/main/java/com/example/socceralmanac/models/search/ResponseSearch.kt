package com.example.socceralmanac.models.search


import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
)