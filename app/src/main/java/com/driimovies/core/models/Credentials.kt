package com.driimovies.core.models

import android.util.Patterns

data class Credentials(val email: String, val password: String) {

    private val emailMatcher = Patterns.EMAIL_ADDRESS

    fun isValid() = isEmailValid() && isPasswordValid()

    fun isEmailValid() = email.let { emailMatcher.matcher(email).matches() }

    fun isPasswordValid() = password?.length in 4..20
}