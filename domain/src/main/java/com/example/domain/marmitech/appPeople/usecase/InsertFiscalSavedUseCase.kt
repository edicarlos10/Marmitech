package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class InsertFiscalSavedUseCase(
    val repository: ILoginRepository
) {
    fun execute(fiscalSaved: FiscalSaved): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            insertFiscalSaved(fiscalSaved)
        )
    }

    private fun insertFiscalSaved(fiscalSaved: FiscalSaved): Observable<Event<Boolean>> {
        return repository.insertFiscalSaved(fiscalSaved).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}