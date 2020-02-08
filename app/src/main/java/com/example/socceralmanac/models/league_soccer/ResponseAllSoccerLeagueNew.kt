package com.example.socceralmanac.models.league_soccer


import com.google.gson.annotations.SerializedName


data class ResponseAllSoccerLeagueNew(

	@field:SerializedName("leagues")
	val leagues: List<LeaguesItem?>? = null
)