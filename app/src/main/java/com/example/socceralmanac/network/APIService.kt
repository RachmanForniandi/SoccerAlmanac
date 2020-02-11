package com.example.socceralmanac.network

import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.detail_match.RootDetailMatch
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface APIService {


    @GET("all_leagues.php")
    fun getSoccerLeagueName(@Query("s")s:String?):Observable<ResponseAllLeague>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("idLeague")id:String?):Observable<RootDetailLeague>

    @GET("eventspastleague.php")
    fun getPreviousMatch(@QueryMap option: HashMap<String, Any>):Observable<ResponseTimeMatch>

    @GET("eventsnextleague.php")
    fun getNextMatch(@QueryMap option: HashMap<String, Any>):Observable<ResponseTimeMatch>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("idEvent")id:String?):Observable<RootDetailMatch>

    @GET("searchevents.php")
    fun getSearchEvents(@Query("query")c:String?):Observable<RootDetailMatch>
}