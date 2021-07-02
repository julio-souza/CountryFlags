package com.codingwolf.countryflags.ui.saved


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwolf.countryflags.domain.model.Country
import com.codingwolf.countryflags.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    fun loadSavedCountries() {
        viewModelScope.launch(Dispatchers.Main) {
            _countries.value = countryRepository.getSavedCountries()
        }
    }

    fun searchCountry(filter: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _countries.value = countryRepository.findCountries(filter)
        }
    }
}