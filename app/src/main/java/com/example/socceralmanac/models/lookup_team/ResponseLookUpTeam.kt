package com.example.socceralmanac.models.lookup_team

import com.google.gson.annotations.SerializedName

data class ResponseLookUpTeam(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)