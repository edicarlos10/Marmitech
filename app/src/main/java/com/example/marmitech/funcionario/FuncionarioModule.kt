package com.example.marmitech.funcionario

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.usecase.*
import com.example.network.weather.marmitech.appPeople.LoginRepository
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.appPeople.local.LoginLocalData
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

}