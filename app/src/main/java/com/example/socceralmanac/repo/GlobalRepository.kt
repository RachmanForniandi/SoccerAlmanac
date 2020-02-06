package com.example.socceralmanac.repo

import com.example.socceralmanac.models.league_soccer.ResponseAllSoccerLeague
import com.example.socceralmanac.network.APIService
import com.example.socceralmanac.network.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.xml.sax.ErrorHandler

class GlobalRepository {

    private var api = NetworkConfig.callApiService()
    private var compositeDisposable = CompositeDisposable()

    fun leagueSoccerName(s: String,responseHandler:(ResponseAllSoccerLeague)->Unit,
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


}