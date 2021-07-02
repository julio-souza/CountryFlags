package com.codingwolf.countryflags.domain.repository

import com.codingwolf.countryflags.data.local.dao.CountryDao
import com.codingwolf.countryflags.data.local.entities.CountryEntity
import com.codingwolf.countryflags.data.local.mapper.toDomainModel
import com.codingwolf.countryflags.data.local.mapper.toEntity
import com.codingwolf.countryflags.data.remote.api.CountryApi
import com.codingwolf.countryflags.domain.RequestResult
import com.codingwolf.countryflags.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository(
    private val countryApi: CountryApi,
    private val countryDao: CountryDao
) {

    suspend fun loadCountries() = withContext(Dispatchers.IO) {
        val listCountryEntity = countryApi
            .getCountryList()
            .map { (code, name) ->
                CountryEntity(code, name)
            }

        countryDao.insert(listCountryEntity)
    }

    suspend fun getCountry(code: String): RequestResult = withContext(Dispatchers.IO) {
        countryDao.getCountryByCode(code)?.let { RequestResult.Found(it.toDomainModel()) }
            ?: RequestResult.NotFound
    }


    suspend fun updateCountry(country: Country) = withContext(Dispatchers.IO) {
        countryDao.update(country.toEntity())
    }

    suspend fun getSavedCountries() = withContext(Dispatchers.IO) {
        countryDao.getSavedCountries().map(CountryEntity::toDomainModel)
    }

    suspend fun findCountries(filter: String) = withContext(Dispatchers.IO) {
        countryDao.getSavedCountriesByNameOrCode(filter).map(CountryEntity::toDomainModel)
    }
}