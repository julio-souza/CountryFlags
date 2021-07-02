package com.codingwolf.countryflags.di

import com.codingwolf.countryflags.data.local.CountryDatabase
import com.codingwolf.countryflags.data.remote.RetrofitProvider
import com.codingwolf.countryflags.data.remote.api.CountryApi
import com.codingwolf.countryflags.domain.repository.CountryRepository
import com.codingwolf.countryflags.ui.MainViewModel
import com.codingwolf.countryflags.ui.flags.FlagsViewModel
import com.codingwolf.countryflags.ui.saved.SavedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val uiModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FlagsViewModel(get()) }
    viewModel { SavedViewModel(get()) }
}

val domainModule = module {
    factory { CountryRepository(get(), get()) }
}

val localModule = module {
    single { CountryDatabase.getDatabase(get()) }
    factory { get<CountryDatabase>().getCountryDao() }
}

val networkModule = module {
    single { RetrofitProvider.instance }
    factory { get<Retrofit>().create(CountryApi::class.java) }
}

val appModules = listOf(uiModule, domainModule, localModule, networkModule)