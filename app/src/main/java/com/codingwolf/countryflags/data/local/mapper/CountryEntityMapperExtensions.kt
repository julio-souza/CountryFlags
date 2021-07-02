package com.codingwolf.countryflags.data.local.mapper

import com.codingwolf.countryflags.data.local.entities.CountryEntity
import com.codingwolf.countryflags.domain.model.Country


fun CountryEntity.toDomainModel() = Country(
    code,
    name,
    saved
)

fun Country.toEntity() = CountryEntity(
    code,
    name,
    saved
)