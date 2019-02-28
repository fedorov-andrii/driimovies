package com.driimovies.app.ui.container

import androidx.lifecycle.ViewModel
import com.driimovies.app.ui.utils.ActionLiveData
import com.driimovies.kit.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ContainerViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel(), Router, CoroutineScope {

    val onChangePageEvent = ActionLiveData<Destination>()

    private val parentJob = Job()
    override val coroutineContext = Dispatchers.Default + parentJob

    init {
        routeToStartDestination()
    }

    private fun routeToStartDestination() = launch {
        val startDestination = getStartDestination()
        route(startDestination)
    }

    private fun getStartDestination(): Destination {
        return if (authenticationRepository.isExistAuthorisedUser()) {
            Destination.Main
        } else {
            Destination.SignIn
        }
    }

    override fun route(destination: Destination) {
        onChangePageEvent.postValue(destination)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}