package com.example.marmitech.apontamento

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.usecase.*
import com.example.network.weather.marmitech.appPeople.ApontamentoRepository
import com.example.network.weather.marmitech.appPeople.local.ApontamentoLocalData
import com.example.network.weather.marmitech.appPeople.local.IApontamentoLocalData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val funcionarioModule = module {
    viewModel {
        ApontamentoViewModel(
            scheduler = get(),
            getFuncionarioUseCase = get(),
            getAllFuncionariosUseCase = get(),
            getAllApontamentoUseCase = get(),
            insertApontamentoUseCase = get(),
            updateApontamentoUseCase = get()
        )
    }

    factory { UpdateApontamentoUseCase(repository = get()) }
    factory { GetFuncionarioUseCase(repository = get()) }
    factory { GetAllFuncionariosUseCase(repository = get()) }
    factory { InsertApontamentoUseCase(repository = get()) }
    factory { GetAllApontamentoCase(repository = get()) }

    single<IApontamentoRepository> { ApontamentoRepository(localData = get(), appDatabase = get()) }
    factory<IApontamentoLocalData> { ApontamentoLocalData(appDatabase = get()) }
}