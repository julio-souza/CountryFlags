package com.codingwolf.countryflags.data.remote.api

import retrofit2.http.GET

private const val COUNTRIES_ENDPOINT = "/en/codes.json"
interface CountryApi {

    @GET(COUNTRIES_ENDPOINT)
    suspend fun getCountryList(): Map<String, String>
}