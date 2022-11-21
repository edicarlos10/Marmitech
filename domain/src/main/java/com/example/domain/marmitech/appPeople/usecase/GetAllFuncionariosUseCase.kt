package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetAllFuncionariosUseCase(
    val repository: IApontamentoRepository
) {
    fun execute(turma: Long): Observable<Event<List<Funcionario>>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getAllFuncionario(turma)
        )
    }

    private fun getAllFuncionario(turma: Long): Observable<Event<List<Funcionario>>> {
        return repository.getAllFuncionario(turma).map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}