package com.driimovies.app.ui.pages.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.driimovies.app.ui.container.Router
import com.driimovies.core.Result
import com.driimovies.kit.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val router: Router,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel(), CoroutineScope {

    val email = MutableLiveData<String>()

    private val parentJob = Job()
    override val coroutineContext = Dispatchers.Default + parentJob

    init {
        launch {
            val result = authenticationRepository.getAuthenticatedUser()
            if (result is Result.Success) {
                email.postValue(result.value.email)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
