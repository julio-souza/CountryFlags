package com.codingwolf.countryflags.domain.model

data class Country(
    val code: String,
    val name: String,
    val saved: Boolean = false
) {
    val flagUrl: String = FLAG_BASE_URL.replace(COUNTRY_CODE, code)

    companion object {
        private const val COUNTRY_CODE = "COUNTRY_CODE"
        private const val FLAG_BASE_URL = "https://flagcdn.com/64x48/$COUNTRY_CODE.png"
    }
}
