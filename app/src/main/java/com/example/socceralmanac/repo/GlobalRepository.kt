package com.example.socceralmanac.repo

import com.example.socceralmanac.models.league_soccer.LeaguesItem
import com.example.socceralmanac.models.league_soccer.ResponseAllLeague
import com.example.socceralmanac.models.lookup_team.ResponseLookUpTeam
import com.example.socceralmanac.models.match_time.EventsTime
import com.example.socceralmanac.models.match_time.ResponseAllEvents
import com.example.socceralmanac.models.search.ResponseSearch
import com.example.socceralmanac.network.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class GlobalRepository {

    private var api = NetworkConfig.callApiService()
    private var compositeDisposable = CompositeDisposable()

    fun leagueSoccerName(s: String, responseHandler:(ResponseAllLeague)->Unit,
                         errorHandler: (Throwable)->Unit){
        val eventsNoted: MutableList<LeaguesItem> = mutableListOf()
        compositeDisposable.add(
            api.getSoccerLeagueName(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*.filter(object: Predicate<ResponseAllLeague> {
                    override fun test(it: ResponseAllLeague)
                            : List<LeaguesItem?>? {
                        return it.leagues?.filter { s -> s?.strSport == "Soccer" }
                    }
                })*/
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })

        )
    }

    fun getMatchEventLastName(idLeague: HashMap<String, Any>, responseHandler:(ResponseAllEvents?)->Unit,
                              errorHandler: (Throwable)->Unit){

        compositeDisposable.add(
            api.getPreviousMatch(idLeague)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
            )
    }

    fun getMatchEventNextName(idLeague: HashMap<String, Any>, responseHandler:(ResponseAllEvents?)->Unit,
                              errorHandler: (Throwable)->Unit){

        compositeDisposable.add(
            api.getNextMatch(idLeague)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }

    fun getBadgeLogoTeam(idTeam:HashMap<String, Any>,responseHandler: (ResponseLookUpTeam) -> Unit,
                         errorHandler: (Throwable)->Unit){
        compositeDisposable.add(
            api.getLogoTeam(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }

    fun getSearchOfTeamMatch(q:HashMap<String, Any>,responseHandler: (ResponseAllEvents) -> Unit,
                             errorHandler: (Throwable)->Unit){
        compositeDisposable.add(
            api.getSearchEvents(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }

    fun onClear(){
        compositeDisposable.clear()
    }

}