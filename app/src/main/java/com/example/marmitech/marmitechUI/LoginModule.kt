package com.example.marmitech.marmitechUI

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.network.weather.marmitech.appPeople.LoginRepository
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.appPeople.local.LoginLocalData
import org.koin.dsl.module

val loginModule = module {
    single<ILoginRepository> {LoginRepository(localData = get(), appDatabase = get())}
    factory<ILoginLocalData> {LoginLocalData(appDatabase = get())}
}