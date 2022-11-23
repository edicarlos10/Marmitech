package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class UpdateApontamentoUseCase(
    val repository: IApontamentoRepository
) {
    fun execute(apontamento: Apontamento): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            updateApontamento(apontamento)
        )
    }

    private fun updateApontamento(apontamento: Apontamento): Observable<Event<Boolean>> {
        return repository.updateApontamento(apontamento).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}