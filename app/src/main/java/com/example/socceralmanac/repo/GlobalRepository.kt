package com.example.socceralmanac.repo

import com.example.socceralmanac.models.detail_match.RootDetailMatch
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeagueNew
import com.example.socceralmanac.models.match_time.ResponseTimeMatch
import com.example.socceralmanac.network.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GlobalRepository {

    private var api = NetworkConfig.callApiService()
    private var compositeDisposable = CompositeDisposable()

    fun leagueSoccerName(s: String, responseHandler:(ResponseAllSoccerLeague)->Unit,
                         errorHandler: (Throwable)->Unit){


        compositeDisposable.add(
            api.getSoccerLeagueName(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )

    }

    fun getMatchEventLastName(idLeague: String,responseHandler:(ResponseTimeMatch)->Unit,
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

    fun getMatchEventNextName(idLeague: String,responseHandler:(ResponseTimeMatch)->Unit,
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

    fun getDetailMatchEvent(idEvent: String,responseHandler:(RootDetailMatch)->Unit,
                              errorHandler: (Throwable)->Unit){

        compositeDisposable.add(
            api.getDetailMatch(idEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }


}