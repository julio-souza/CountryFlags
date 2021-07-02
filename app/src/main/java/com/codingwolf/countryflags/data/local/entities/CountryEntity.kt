package com.codingwolf.countryflags.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codingwolf.countryflags.data.local.entities.CountryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CountryEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val saved: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "country"
    }
}