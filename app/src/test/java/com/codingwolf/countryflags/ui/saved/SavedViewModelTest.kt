package com.codingwolf.countryflags.ui.saved

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.codingwolf.countryflags.domain.model.Country
import com.codingwolf.countryflags.domain.repository.CountryRepository
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SavedViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockedRepo = mockk<CountryRepository>(relaxed = true) {
        coEvery { getSavedCountries() } returns savedCountries
        coEvery { findCountries(VALID_FILTER) } returns filteredCountries
        coEvery { findCountries(NOT_FOUND_FILTER) } returns emptyList()
    }

    private val viewModel: SavedViewModel = SavedViewModel(mockedRepo)

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `Assert that countries will be populated with all saved countries when loadSavedCountries is called`() {
        val observer = mockk<Observer<List<Country>>>(relaxed = true)
        viewModel.countries.observeForever(observer)

        viewModel.loadSavedCountries()

        verify { observer.onChanged(savedCountries) }
        confirmVerified(observer)
    }

    @Test
    fun `Assert that countries will be populated with filtered saved countries when searchCountry is called with a valid filter`() {
        val observer = mockk<Observer<List<Country>>>(relaxed = true)
        viewModel.countries.observeForever(observer)

        viewModel.searchCountry(VALID_FILTER)

        verify { observer.onChanged(filteredCountries) }
        confirmVerified(observer)
    }

    @Test
    fun `Assert that countries will be populated with empty list when searchCountry is called with an invalid filter`() {
        val observer = mockk<Observer<List<Country>>>(relaxed = true)
        viewModel.countries.observeForever(observer)

        viewModel.searchCountry(NOT_FOUND_FILTER)

        verify { observer.onChanged(emptyList()) }
        confirmVerified(observer)
    }

    companion object {
        val savedCountries = listOf(
            Country(
                "BR",
                "Brazil",
                true
            ),
            Country(
                "IE",
                "Ireland",
                true
            ),
            Country(
                "US",
                "United Stated",
                true
            ),
            Country(
                "DE",
                "Germany",
                true
            ),
            Country(
                "CN",
                "China",
                true
            ),
        )
        const val VALID_FILTER = "BR"
        val filteredCountries = listOf(
            Country(
                "BR",
                "Brazil",
                true
            )
        )
        const val NOT_FOUND_FILTER = "ZZ"
    }
}