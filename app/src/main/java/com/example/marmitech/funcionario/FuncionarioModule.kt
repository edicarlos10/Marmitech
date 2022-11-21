package com.example.marmitech.funcionario

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.usecase.GetAllFuncionariosUseCase
import com.example.domain.marmitech.appPeople.usecase.GetFuncionarioUseCase
import com.example.network.weather.marmitech.appPeople.ApontamentoRepository
import com.example.network.weather.marmitech.appPeople.local.ApontamentoLocalData
import com.example.network.weather.marmitech.appPeople.local.IApontamentoLocalData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val funcionarioModule = module {
    viewModel {
        FuncionarioViewModel(
            scheduler = get(),
            getFuncionarioUseCase = get(),
            getAllFuncionariosUseCase = get()
        )
    }

    factory { GetFuncionarioUseCase(repository = get()) }
    factory { GetAllFuncionariosUseCase(repository = get()) }

    single<IApontamentoRepository> { ApontamentoRepository(localData = get(), appDatabase = get()) }
    factory<IApontamentoLocalData> { ApontamentoLocalData(appDatabase = get()) }
}