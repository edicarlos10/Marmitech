package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class RemoveAllFiscalSavedUseCase(
    val repository: ILoginRepository
) {
    fun execute(): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            deleteAllFiscalSaved()
        )
    }

    private fun deleteAllFiscalSaved(): Observable<Event<Boolean>> {
        return repository.deleteAllFiscalSaved()
            .toSingle {
                Event.data(true)
            }.onErrorReturn { Event.error(it) }.toObservable()
    }
}