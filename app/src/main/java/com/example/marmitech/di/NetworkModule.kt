package com.example.marmitech.di

import com.example.domain.marmitech.util.ISchedulerProvider
import com.example.marmitech.utils.domain.AppSchedulerProvider
import org.koin.dsl.module

val networkModule = module {
    single<ISchedulerProvider> { AppSchedulerProvider() }
}