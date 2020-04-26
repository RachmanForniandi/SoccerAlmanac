package com.example.socceralmanac.network

import com.example.socceralmanac.models.detail_league.RootDetailLeague
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.models.search.ResponseSearch
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface APIService {


    @GET("all_leagues.php")
    fun getSoccerLeagueName(@Query("s")s:String?):Observable<ResponseAllLeague>

    @GET("lookupleague.php")
    fun getDetailLeague(@QueryMap option: HashMap<String, Any>):Single<RootDetailLeague>

    @GET("eventspastleague.php")
    fun getPreviousMatch(@QueryMap option: HashMap<String, Any>):Observable<ResponseAllEvents?>

    @GET("eventsnextleague.php")
    fun getNextMatch(@QueryMap option: HashMap<String, Any>):Observable<ResponseAllEvents?>

    @GET("lookupteam.php")
    fun getLogoTeam(@QueryMap option: HashMap<String, Any>):Single<ResponseLookUpTeam>

    @GET("searchevents.php")
    fun getSearchEvents(@QueryMap option: HashMap<String, Any>):Observable<ResponseAllEvents>
}