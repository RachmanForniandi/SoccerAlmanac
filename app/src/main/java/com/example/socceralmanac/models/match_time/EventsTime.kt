package com.example.socceralmanac.models.match_time


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName="subscriber_data_match")
data class EventsTime(
	@PrimaryKey(autoGenerate = true)

	@ColumnInfo(name = "intHomeShots")
	@field:SerializedName("intHomeShots")
	val intHomeShots: Any? = null,

	@ColumnInfo(name ="strSport")
	@field:SerializedName("strSport")
	val strSport: String? = null,

	@ColumnInfo(name ="strHomeLineupDefense")
	@field:SerializedName("strHomeLineupDefense")
	val strHomeLineupDefense: String? = null,

	@ColumnInfo(name ="strAwayLineupSubstitutes")
	@field:SerializedName("strAwayLineupSubstitutes")
	val strAwayLineupSubstitutes: String? = null,

	@ColumnInfo(name ="strAwayLineupSubstitutes")
	@field:SerializedName("strTweet1")
	val strTweet1: Any? = null,

	@ColumnInfo(name ="strAwayLineupSubstitutes")
	@field:SerializedName("strTweet2")
	val strTweet2: Any? = null,

	@ColumnInfo(name ="strTweet3")
	@field:SerializedName("strTweet3")
	val strTweet3: Any? = null,

	@ColumnInfo(name ="idLeague")
	@field:SerializedName("idLeague")
	val idLeague: String? = null,

	@ColumnInfo(name ="idSoccerXML")
	@field:SerializedName("idSoccerXML")
	val idSoccerXML: String? = null,

	@ColumnInfo(name ="strHomeLineupForward")
	@field:SerializedName("strHomeLineupForward")
	val strHomeLineupForward: String? = null,

	@ColumnInfo(name ="strTVStation")
	@field:SerializedName("strTVStation")
	val strTVStation: Any? = null,

	@ColumnInfo(name ="strHomeGoalDetails")
	@field:SerializedName("strHomeGoalDetails")
	val strHomeGoalDetails: String? = null,

	@ColumnInfo(name ="strVideo")
	@field:SerializedName("strVideo")
	val strVideo: Any? = null,

	@ColumnInfo(name ="strAwayLineupGoalkeeper")
	@field:SerializedName("strAwayLineupGoalkeeper")
	val strAwayLineupGoalkeeper: String? = null,

	@ColumnInfo(name ="strAwayLineupMidfield")
	@field:SerializedName("strAwayLineupMidfield")
	val strAwayLineupMidfield: String? = null,

	@ColumnInfo(name ="idEvent")
	@field:SerializedName("idEvent")
	val idEvent: String? = null,

	@ColumnInfo(name ="intRound")
	@field:SerializedName("intRound")
	val intRound: String? = null,

	@ColumnInfo(name ="strHomeYellowCards")
	@field:SerializedName("strHomeYellowCards")
	val strHomeYellowCards: String? = null,

	@ColumnInfo(name ="idHomeTeam")
	@field:SerializedName("idHomeTeam")
	val idHomeTeam: String? = null,

	@ColumnInfo(name ="intHomeScore")
	@field:SerializedName("intHomeScore")
	val intHomeScore: Any? = null,

	@ColumnInfo(name ="dateEvent")
	@field:SerializedName("dateEvent")
	val dateEvent: String? = null,

	@ColumnInfo(name ="strAwayLineupSubstitutes")
	@field:SerializedName("strCountry")
	val strCountry: Any? = null,

	@ColumnInfo(name ="strAwayTeam")
	@field:SerializedName("strAwayTeam")
	val strAwayTeam: String? = null,

	@ColumnInfo(name ="strHomeLineupMidfield")
	@field:SerializedName("strHomeLineupMidfield")
	val strHomeLineupMidfield: String? = null,

	@ColumnInfo(name ="strDate")
	@field:SerializedName("strDate")
	val strDate: String? = null,

	@ColumnInfo(name ="strHomeFormation")
	@field:SerializedName("strHomeFormation")
	val strHomeFormation: Any? = null,

	@ColumnInfo(name ="strMap")
	@field:SerializedName("strMap")
	val strMap: Any? = null,

	@ColumnInfo(name ="idAwayTeam")
	@field:SerializedName("idAwayTeam")
	val idAwayTeam: String? = null,

	@ColumnInfo(name ="strAwayRedCards")
	@field:SerializedName("strAwayRedCards")
	val strAwayRedCards: String? = null,

	@ColumnInfo(name ="strBanner")
	@field:SerializedName("strBanner")
	val strBanner: Any? = null,

	@ColumnInfo(name ="strFanart")
	@field:SerializedName("strFanart")
	val strFanart: Any? = null,

	@ColumnInfo(name ="strDescriptionEN")
	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: Any? = null,

	@ColumnInfo(name ="dateEventLocal")
	@field:SerializedName("dateEventLocal")
	val dateEventLocal: Any? = null,

	@ColumnInfo(name ="strResult")
	@field:SerializedName("strResult")
	val strResult: Any? = null,

	@ColumnInfo(name ="strCircuit")
	@field:SerializedName("strCircuit")
	val strCircuit: Any? = null,

	@ColumnInfo(name ="intAwayShots")
	@field:SerializedName("intAwayShots")
	val intAwayShots: Any? = null,

	@ColumnInfo(name ="strFileName")
	@field:SerializedName("strFilename")
	val strFilename: String? = null,

	@ColumnInfo(name ="strTime")
	@field:SerializedName("strTime")
	val strTime: String? = null,

	@ColumnInfo(name ="strAwayGoalDetails")
	@field:SerializedName("strAwayGoalDetails")
	val strAwayGoalDetails: String? = null,

	@ColumnInfo(name ="strAwayLineupForward")
	@field:SerializedName("strAwayLineupForward")
	val strAwayLineupForward: String? = null,

	@ColumnInfo(name ="strTimeLocal")
	@field:SerializedName("strTimeLocal")
	val strTimeLocal: String? = null,

	@ColumnInfo(name ="strLocked")
	@field:SerializedName("strLocked")
	val strLocked: String? = null,

	@ColumnInfo(name ="strSeason")
	@field:SerializedName("strSeason")
	val strSeason: String? = null,

	@ColumnInfo(name ="intSpectators")
	@field:SerializedName("intSpectators")
	val intSpectators: Any? = null,

	@ColumnInfo(name ="strEventAlternate")
	@field:SerializedName("strEventAlternate")
	val strEventAlternate: String? = null,

	@ColumnInfo(name ="strHomeRedCards")
	@field:SerializedName("strHomeRedCards")
	val strHomeRedCards: String? = null,

	@ColumnInfo(name ="strHomeLineupGoalkeeper")
	@field:SerializedName("strHomeLineupGoalkeeper")
	val strHomeLineupGoalkeeper: String? = null,

	@ColumnInfo(name ="strHomeLineupSubstitutes")
	@field:SerializedName("strHomeLineupSubstitutes")
	val strHomeLineupSubstitutes: String? = null,

	@ColumnInfo(name ="strAwayFormation")
	@field:SerializedName("strAwayFormation")
	val strAwayFormation: Any? = null,

	@ColumnInfo(name ="strEvent")
	@field:SerializedName("strEvent")
	val strEvent: String? = null,

	@ColumnInfo(name ="strAwayYellowCards")
	@field:SerializedName("strAwayYellowCards")
	val strAwayYellowCards: String? = null,

	@ColumnInfo(name ="strAwayLineupDefense")
	@field:SerializedName("strAwayLineupDefense")
	val strAwayLineupDefense: String? = null,

	@ColumnInfo(name ="strHomeTeam")
	@field:SerializedName("strHomeTeam")
	val strHomeTeam: String? = null,

	@ColumnInfo(name ="strThumb")
	@field:SerializedName("strThumb")
	val strThumb: Any? = null,

	@ColumnInfo(name ="strLeague")
	@field:SerializedName("strLeague")
	val strLeague: String? = null,

	@ColumnInfo(name ="intAwayScore")
	@field:SerializedName("intAwayScore")
	val intAwayScore: Any? = null,

	@ColumnInfo(name ="strCity")
	@field:SerializedName("strCity")
	val strCity: Any? = null,

	@ColumnInfo(name ="strPoster")
	@field:SerializedName("strPoster")
	val strPoster: Any? = null
):Serializable



