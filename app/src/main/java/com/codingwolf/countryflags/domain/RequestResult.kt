package com.codingwolf.countryflags.domain

import com.codingwolf.countryflags.domain.model.Country

sealed interface RequestResult {
    class Found(val country: Country): RequestResult
    object NotFound: RequestResult
}