package com.driimovies.app.ui.pages.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import com.driimovies.app.ui.common.ErrorMessageFactory
import com.driimovies.app.ui.container.Destination
import com.driimovies.app.ui.container.Router
import com.driimovies.core.AppException
import com.driimovies.core.Result
import com.driimovies.core.models.Credentials
import com.driimovies.kit.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignInViewModel(
    private val router: Router,
    private val authenticationRepository: AuthenticationRepository,
    private val errorMessageFactory: ErrorMessageFactory
) : ViewModel(), CoroutineScope {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    val isEmailEnabled = MutableLiveData<Boolean>().apply { value = true }
    val isPasswordEnabled = MutableLiveData<Boolean>().apply { value = true }
    val isSignInEnabled = MutableLiveData<Boolean>().apply { value = true }
    val isSignUpEnabled = MutableLiveData<Boolean>().apply { value = true }

    val isProgressBarVisible = MutableLiveData<Boolean>()
    val isErrorVisible = Transformations.map(error, { it.isNotBlank()})

    private val parentJob = Job()
    override val coroutineContext = Dispatchers.Default + parentJob

    fun signIn() = launch {
        val credentials = Credentials(email.value ?: "", password.value ?: "")
        val isCredentialsValid = credentials.isValid()

        if (isCredentialsValid) {
            error.postValue("")
            isProgressBarVisible.postValue(true)
            setEnabledUi(false)

            when(val result = authenticationRepository.signIn(credentials)) {
                is Result.Success -> router.route(Destination.Main)
                is Result.Error -> {
                    val errorMessage = errorMessageFactory.getError(result.error)
                    error.postValue(errorMessage)
                    isProgressBarVisible.postValue(false)
                    setEnabledUi(true)
                }
            }
        } else {
            val validationError = AppException.CredentialsNotValidException()
            val errorMessage = errorMessageFactory.getError(validationError)
            error.postValue(errorMessage)
        }
    }

    private fun setEnabledUi(enabled: Boolean) {
        isEmailEnabled.postValue(enabled)
        isPasswordEnabled.postValue(enabled)
        isSignInEnabled.postValue(enabled)
        isSignUpEnabled.postValue(enabled)
    }

    fun signUp() {
        router.route(Destination.SignUp)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}
