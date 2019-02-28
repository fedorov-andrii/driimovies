package com.driimovies.extension

import android.content.Context
import com.driimovies.app.application.App

fun Context.asApp() = this.applicationContext as App