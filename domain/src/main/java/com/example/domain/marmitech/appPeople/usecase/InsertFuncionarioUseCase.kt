package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class InsertFuncionarioUseCase(
    val repository: ILoginRepository
) {
    fun execute(funcionario: Funcionario): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            insertFuncionario(funcionario)
        )
    }

    private fun insertFuncionario(funcionario: Funcionario): Observable<Event<Boolean>> {
        return repository.insertFuncionario(funcionario).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}