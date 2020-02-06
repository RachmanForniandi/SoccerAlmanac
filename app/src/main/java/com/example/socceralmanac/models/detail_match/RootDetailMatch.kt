package com.example.socceralmanac.models.detail_match


import com.google.gson.annotations.SerializedName

data class RootDetailMatch(

	@field:SerializedName("events")
	val events: List<EventsDetailItem?>? = null
)