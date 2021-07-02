package com.codingwolf.countryflags.data.remote

import com.codingwolf.countryflags.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {

    val instance: Retrofit
        get() = buildRetrofit()

    const val baseUrl = "https://flagcdn.com/"

    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(buildLoggingInterceptor()).build()

    private fun buildLoggingInterceptor(debugMode: Boolean = BuildConfig.DEBUG): Interceptor =
        HttpLoggingInterceptor().apply { level = if (debugMode) Level.BODY else Level.NONE }

    private fun buildRetrofit(): Retrofit = Retrofit
        .Builder()
        .apply {
            baseUrl(baseUrl)
            addConverterFactory(MoshiConverterFactory.create())
            client(buildOkHttpClient())
        }.build()
}
