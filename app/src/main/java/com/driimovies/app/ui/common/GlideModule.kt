package com.driimovies.app.ui.common

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.driimovies.extension.asApp
import java.io.InputStream

@GlideModule
class GlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        val baseUrl = context.asApp().dependencyContainer.imageBaseUrl
        val urlLoaderFactory = UrlLoader.UrlLoaderFactory(baseUrl)
        registry.prepend(String::class.java, InputStream::class.java, urlLoaderFactory)
    }

}