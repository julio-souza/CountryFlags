package com.codingwolf.countryflags.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwolf.countryflags.data.local.CountryDatabase.Companion.DATABASE_VERSION
import com.codingwolf.countryflags.data.local.dao.CountryDao
import com.codingwolf.countryflags.data.local.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun getCountryDao(): CountryDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "country_db"

        @Volatile
        private var db: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase {
            if (db == null) {
                synchronized(CountryDatabase::class) {
                    db = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return db!!
        }
    }
}