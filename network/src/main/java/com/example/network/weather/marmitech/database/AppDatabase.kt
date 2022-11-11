package com.example.network.weather.marmitech.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.network.weather.marmitech.appPeople.local.database.FiscalDao
import com.example.network.weather.marmitech.appPeople.local.database.FiscalEntity
import com.example.network.weather.marmitech.appPeople.local.database.TurmaDao
import com.example.network.weather.marmitech.appPeople.local.database.TurmaEntity

@Database(
    entities = [FiscalEntity::class, TurmaEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fiscalDao(): FiscalDao
    abstract fun turmaDao(): TurmaDao
}