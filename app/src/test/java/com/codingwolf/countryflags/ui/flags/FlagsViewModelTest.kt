package com.codingwolf.countryflags.ui.flags

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.codingwolf.countryflags.domain.RequestResult
import com.codingwolf.countryflags.domain.model.Country
import com.codingwolf.countryflags.domain.repository.CountryRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class FlagsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockedRepo = mockk<CountryRepository>(relaxed = true) {
        coEvery { getCountry(VALID_CODE) } returns RequestResult.Found(VALID_COUNTRY)
        coEvery { getCountry(NOT_FOUND_CODE) } returns RequestResult.NotFound
    }

    private val viewModel = FlagsViewModel(mockedRepo)

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `Assert that ViewState will be CountryFound when findCountry is called with valid Code`() {
        val expected = VALID_COUNTRY
        val observer = mockk<Observer<FlagsViewState>>(relaxed = true)
        viewModel.viewState.observeForever(observer)

        viewModel.findCountry(VALID_CODE)

        verify {
            observer.onChanged(
                withArg {
                    assertTrue(it is FlagsViewState.CountryFound)
                    assertEquals(expected, (it as FlagsViewState.CountryFound).country)
                }
            )
        }

        confirmVerified(observer)
    }

    @Test
    fun `Assert that ViewState will be InvalidCode when findCountry is called with an invalid Code`() {
        val observer = mockk<Observer<FlagsViewState>>(relaxed = true)
        viewModel.viewState.observeForever(observer)

        viewModel.findCountry(INVALID_CODE)

        verify { observer.onChanged(FlagsViewState.InvalidCode) }

        confirmVerified(observer)
    }

    @Test
    fun `Assert that ViewState will be NotFound when findCountry is called with a code that doesn't exist on the database`() {
        val observer = mockk<Observer<FlagsViewState>>(relaxed = true)
        viewModel.viewState.observeForever(observer)

        viewModel.findCountry(NOT_FOUND_CODE)

        verify { observer.onChanged(FlagsViewState.NotFound) }

        confirmVerified(observer)
    }

    @Test
    fun `Assert that updateCountry will be called with valid country and saved option when likeCountry is called`() {
        viewModel.findCountry(VALID_CODE)
        viewModel.likeCountry(true)

        coVerifyOrder {
            mockedRepo.getCountry(VALID_CODE)
            mockedRepo.updateCountry(VALID_COUNTRY_SAVED)
        }
        confirmVerified(mockedRepo)
    }

    companion object {
        const val VALID_CODE = "IE"
        const val NOT_FOUND_CODE = "ZZ"
        const val INVALID_CODE = "00"
        const val VALID_NAME = "Ireland"
        val VALID_COUNTRY = Country(VALID_CODE, VALID_NAME)
        val VALID_COUNTRY_SAVED = Country(VALID_CODE, VALID_NAME, true)
    }
}
