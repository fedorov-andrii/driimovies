package com.driimovies.app.application

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.driimovies.app.ui.container.Destination
import com.driimovies.app.ui.container.Router
import com.driimovies.app.ui.utils.ActionLiveData

class App: Application(), Router {

    val dependencyContainer by lazy { DependencyContainer(this) }
    val onChangePageEvent = ActionLiveData<Destination>()

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
    }

    override fun route(destination: Destination) {
        onChangePageEvent.postValue(destination)
    }
}

