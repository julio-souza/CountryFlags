package com.codingwolf.countryflags.ui.flags

import com.codingwolf.countryflags.domain.model.Country

sealed class FlagsViewState {
    object InvalidCode : FlagsViewState()
    object NotFound : FlagsViewState()
    class CountryFound(val country: Country) : FlagsViewState()
}
