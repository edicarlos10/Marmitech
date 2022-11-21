package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class InsertApontamentoUseCase(
    val repository: IApontamentoRepository
) {
    fun execute(apontamento: Apontamento): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            insertApontamento(apontamento)
        )
    }

    private fun insertApontamento(apontamento: Apontamento): Observable<Event<Boolean>> {
        return repository.insertApontamento(apontamento).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}