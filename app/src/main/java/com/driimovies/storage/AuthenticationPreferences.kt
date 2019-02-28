package com.driimovies.storage

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.blockingBulk
import com.driimovies.core.AppException
import com.driimovies.core.Result
import com.driimovies.core.models.Credentials
import com.driimovies.core.models.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser

object AuthenticationPreferences : KotprefModel() {
    private val gson = GsonBuilder().create()
    private var signedInUserCredentials by stringPref()
    private var signedUpUserCredentialsJson by stringPref()

    fun signedUp(credentials: Credentials): Result<Unit, Exception> {
        val user = User(credentials.email)
        return if (AuthenticationPreferences.isUserExist(user)) {
            Result.Error(AppException.UserAlreadyExistException())
        } else {
            blockingBulk {
                val newWorkspaces = getAll().toMutableSet()
                newWorkspaces.add(credentials)
                signedUpUserCredentialsJson = newWorkspaces.toJson()
                signIn(credentials)
            }
            Result.Success(Unit)
        }
    }

    fun signIn(credentials: Credentials): Result<Unit, Exception> {
        return if (checkCredentials(credentials)) {
            signedInUserCredentials = gson.toJson(credentials)
            Result.Success(Unit)
        } else {
            Result.Error(AppException.UserNotExistExistException())
        }
    }

    fun signOut(): Result<Unit, Exception> {
        signedInUserCredentials = ""
        return Result.Success(Unit)
    }

    fun getSignedInUser(): User? {
        return if (signedInUserCredentials.isNotBlank()) {
            val credentials = gson.fromJson(signedInUserCredentials, Credentials::class.java)
            User(credentials.email)
        } else {
            null
        }
    }

    fun checkCredentials(credentials: Credentials): Boolean {
        return getAll().toMutableSet().contains(credentials)
    }

    fun isUserExist(user: User): Boolean {
        return getAll().toMutableSet().firstOrNull { it.email == user.email } != null
    }


    private fun getAll() = signedUpUserCredentialsJson.fromJson()

    private fun String.fromJson(): Set<Credentials> {
        val json = JsonParser().parse(this)

        return if (json.isJsonArray) {
            json.asJsonArray.map { gson.fromJson(it, Credentials::class.java) }.toHashSet()
        } else {
            emptySet()
        }
    }

    private fun Set<Credentials>.toJson(): String {
        val jsonArray = JsonArray()
        forEach {
            val workspaceJs = gson.toJsonTree(it)
            jsonArray.add(workspaceJs)
        }
        return jsonArray.toString()
    }


}