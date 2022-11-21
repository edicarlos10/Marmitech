package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetAllApontamentoCase(
    val repository: IApontamentoRepository
) {
    fun execute(): Observable<Event<List<Apontamento>>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getAllApontamento()
        )
    }

    private fun getAllApontamento(): Observable<Event<List<Apontamento>>> {
        return repository.getAllApontamento().map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}