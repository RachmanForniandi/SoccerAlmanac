package com.example.socceralmanac.models.league_soccer

import com.google.gson.annotations.SerializedName

data class ResponseAllSoccerLeague(

	@field:SerializedName("countrys")
	val countrys: List<CountrysItem?>? = null
)