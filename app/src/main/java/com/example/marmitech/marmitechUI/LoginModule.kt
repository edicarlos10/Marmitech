package com.example.marmitech.marmitechUI

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.usecase.GetFiscalSavedUseCase
import com.example.domain.marmitech.appPeople.usecase.GetFiscalUseCase
import com.example.domain.marmitech.appPeople.usecase.GetTurmaUseCase
import com.example.network.weather.marmitech.appPeople.LoginRepository
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.appPeople.local.LoginLocalData
import org.koin.dsl.module

val loginModule = module {
    single<ILoginRepository> { LoginRepository(localData = get(), appDatabase = get()) }
    factory<ILoginLocalData> { LoginLocalData(appDatabase = get()) }
    factory { GetFiscalUseCase(repository = get()) }
    factory { GetTurmaUseCase(repository = get()) }
    factory { GetFiscalSavedUseCase(repository = get()) }
}