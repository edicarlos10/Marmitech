package com.example.marmitech.login

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.usecase.*
import com.example.network.weather.marmitech.appPeople.LoginRepository
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.appPeople.local.LoginLocalData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
            scheduler = get(),
            getFiscalUseCase = get(),
            getFiscalSavedUseCase = get(),
            insertTurmaUseCase = get(),
            insertFiscalSavedUseCase = get(),
            insertFiscalUseCase = get(),
            getTurmaUseCase = get(),
            removeAllFiscalSavedUseCase = get()
        )
    }

    factory { GetFiscalUseCase(repository = get()) }
    factory { GetTurmaUseCase(repository = get()) }
    factory { GetFiscalSavedUseCase(repository = get()) }
    factory { InsertTurmaUseCase(repository = get()) }
    factory { InsertFiscalSavedUseCase(repository = get()) }
    factory { InsertFiscalUseCase(repository = get()) }
    factory { RemoveAllFiscalSavedUseCase(repository = get()) }

    single<ILoginRepository> { LoginRepository(localData = get(), appDatabase = get()) }
    factory<ILoginLocalData> { LoginLocalData(appDatabase = get()) }
}