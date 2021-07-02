package com.codingwolf.countryflags.ui.flags

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwolf.countryflags.domain.RequestResult
import com.codingwolf.countryflags.domain.model.Country
import com.codingwolf.countryflags.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlagsViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _viewState = MutableLiveData<FlagsViewState>()
    val viewState: LiveData<FlagsViewState> get() = _viewState

    private var currentCountry: Country? = null

    fun findCountry(countryCode: String) {
        if (countryCode.isBlank() || !countryCode.contains(NUMBER_REGEX)) {
            _viewState.value = FlagsViewState.InvalidCode
        } else {
            viewModelScope.launch(Dispatchers.Main) {
                val result = countryRepository.getCountry(countryCode)
                onGetCountryResult(result)
            }
        }
    }

    private fun onGetCountryResult(result: RequestResult) {
        when (result) {
            is RequestResult.Found -> {
                currentCountry = result.country
                _viewState.value = FlagsViewState.CountryFound(result.country)
            }
            RequestResult.NotFound -> {
                _viewState.value = FlagsViewState.NotFound
            }
        }
    }

    fun likeCountry(checked: Boolean) {
        currentCountry?.let { country ->
            currentCountry = country.copy(saved = checked)
            viewModelScope.launch(Dispatchers.Main) {
                countryRepository.updateCountry(currentCountry!!)
            }
        }
    }

    companion object {
        private val NUMBER_REGEX = Regex("[A-Z]{2}")
    }
}