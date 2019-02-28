package com.driimovies.app.ui.common

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream


class UrlLoader(
    private val baseUrl: String,
    urlLoader: ModelLoader<GlideUrl, InputStream>
) : BaseGlideUrlLoader<String>(urlLoader) {

    class UrlLoaderFactory(private val baseUrl: String) : ModelLoaderFactory<String, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            val loader = multiFactory.build(GlideUrl::class.java, InputStream::class.java)
            return UrlLoader(baseUrl, loader)
        }

        override fun teardown() {
        }
    }

    override fun getUrl(model: String?, width: Int, height: Int, options: Options?): String {
        return "$baseUrl/$model"
    }

    override fun handles(model: String): Boolean {
        return model.startsWith("/")
    }
}