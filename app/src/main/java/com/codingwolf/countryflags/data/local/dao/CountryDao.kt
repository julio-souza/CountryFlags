package com.codingwolf.countryflags.data.local.dao

import androidx.room.*
import com.codingwolf.countryflags.data.local.entities.CountryEntity

@Dao
interface CountryDao {

    @Query(value = "SELECT * FROM country WHERE UPPER(code) = UPPER(:code)")
    suspend fun getCountryByCode(code: String): CountryEntity?

    @Query("SELECT * FROM country WHERE saved = 1")
    suspend fun getSavedCountries(): List<CountryEntity>

    @Query(value = "SELECT * FROM country WHERE saved = 1 AND (code like '%'||:filter||'%' OR name like '%'||:filter||'%')")
    suspend fun getSavedCountriesByNameOrCode(filter: String): List<CountryEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(countries: List<CountryEntity>)

}