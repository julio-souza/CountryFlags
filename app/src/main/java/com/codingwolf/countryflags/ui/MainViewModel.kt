package com.codingwolf.countryflags.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwolf.countryflags.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    fun checkCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            countryRepository.loadCountries()
        }
    }
}