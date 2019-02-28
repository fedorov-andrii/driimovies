package com.driimovies.kit.repositories

import com.driimovies.core.Result
import com.driimovies.core.models.Credentials
import com.driimovies.core.models.User
import com.driimovies.storage.AuthenticationPreferences

class AuthenticationRepository {

    fun signUp(credentials: Credentials): Result<Unit, Exception> {
        return AuthenticationPreferences.signedUp(credentials)
    }

    fun signIn(credentials: Credentials): Result<Unit, Exception> {
        return AuthenticationPreferences.signIn(credentials)
    }

    fun signOut(): Result<Unit, Exception> {
        return AuthenticationPreferences.signOut()
    }

    fun isExistAuthorisedUser(): Boolean {
        return AuthenticationPreferences.getSignedInUser() != null
    }

    fun getAuthenticatedUser(): Result<User, Exception> {
        val authenticatedUser = AuthenticationPreferences.getSignedInUser()
        return if (authenticatedUser == null) {
            Result.Error(IllegalStateException())
        } else {
            Result.Success(authenticatedUser)
        }
    }
}