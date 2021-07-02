package com.codingwolf.countryflags.domain.repository

import com.codingwolf.countryflags.data.local.dao.CountryDao
import com.codingwolf.countryflags.data.local.entities.CountryEntity
import com.codingwolf.countryflags.data.remote.api.CountryApi
import com.codingwolf.countryflags.domain.RequestResult
import com.codingwolf.countryflags.domain.model.Country
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CountryRepositoryTest {

    private val defaultApi = mockk<CountryApi>(relaxed = true)

    @Test
    fun `Assert that insertCountries is called when a valid list of countries is obtained from Api's getCountryList`() =
        runBlocking {
            val loadCountriesApiResult = mapOf(
                "BR" to "Brazil",
                "IE" to "Ireland",
                "US" to "United States"
            )
            val mockedApi = mockk<CountryApi> {
                coEvery { getCountryList() } returns loadCountriesApiResult
            }
            val convertedApiResult = listOf(
                CountryEntity("BR", "Brazil"),
                CountryEntity("IE", "Ireland"),
                CountryEntity("US", "United States")
            )
            val mockedDao = mockk<CountryDao> {
                coEvery { insert(convertedApiResult) } returns Unit
            }
            val repository = CountryRepository(mockedApi, mockedDao)

            repository.loadCountries()

            coVerify { mockedDao.insert(convertedApiResult) }
        }

    @Test
    fun `Assert that valid list of countries will be returned when getSavedCountries is called`() =
        runBlocking {
            val expectedResult = listOf(
                Country("BR", "Brazil", true),
                Country("IE", "Ireland", true)
            )
            val mockedDao = mockk<CountryDao> {
                coEvery { getSavedCountries() } returns listOf(
                    CountryEntity("BR", "Brazil", true),
                    CountryEntity("IE", "Ireland", true)
                )
            }
            val repository = CountryRepository(defaultApi, mockedDao)

            val result = repository.getSavedCountries()

            assertEquals(expectedResult, result)
        }

    @Test
    fun `Assert that getCountry returns expected result when valid code is used`() = runBlocking {
        val validCode = "IE"
        val mockedDao = mockk<CountryDao> {
            coEvery { getCountryByCode(validCode) } returns CountryEntity("IE", "Ireland", true)
        }
        val expected = Country("IE", "Ireland", true)
        val repository = CountryRepository(defaultApi, mockedDao)

        val result: RequestResult = repository.getCountry(validCode)

        assertTrue(result is RequestResult.Found)
        assertEquals(expected, (result as RequestResult.Found).country)
    }

    @Test
    fun `Assert that NotFound is returned when getCountry is called with invalid code`() =
        runBlocking {
            val invalidCode = "ZZ"
            val mockedDao = mockk<CountryDao> {
                coEvery { getCountryByCode(invalidCode) } returns null
            }
            val expected = RequestResult.NotFound
            val repository = CountryRepository(defaultApi, mockedDao)

            val result: RequestResult = repository.getCountry(invalidCode)

            assertEquals(result, expected)
        }


}