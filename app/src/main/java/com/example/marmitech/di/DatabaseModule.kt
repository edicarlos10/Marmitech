package com.example.marmitech.di

import android.content.Context
import androidx.room.Room
import com.example.network.weather.marmitech.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val APP_DATABASE_MARMITECH = "app_database_marmitech"

val databaseModule = module {
    single { createAppDatabase(androidContext()) }
}

private fun createAppDatabase(context: Context) = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    APP_DATABASE_MARMITECH
)
