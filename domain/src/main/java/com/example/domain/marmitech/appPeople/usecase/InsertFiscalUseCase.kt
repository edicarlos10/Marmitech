package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class InsertFiscalUseCase(
    val repository: ILoginRepository
) {
    fun execute(fiscal: Fiscal): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            insertFiscal(fiscal)
        )
    }

    private fun insertFiscal(fiscal: Fiscal): Observable<Event<Boolean>> {
        return repository.insertFiscal(fiscal).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}