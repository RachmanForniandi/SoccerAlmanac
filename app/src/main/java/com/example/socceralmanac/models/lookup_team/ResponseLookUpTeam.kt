package com.example.socceralmanac.models.lookup_team

import com.google.gson.annotations.SerializedName

data class ResponseLookUpTeam(
	@SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)