package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetFiscalSavedUseCase(
    val repository: ILoginRepository
) {
    fun execute(matricula: Long, turma: Long): Observable<Event<FiscalSaved>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getFiscalSaved(matricula, turma)
        )
    }

    private fun getFiscalSaved(matricula: Long, turma: Long): Observable<Event<FiscalSaved>> {
        return repository.getFiscalSaved(matricula, turma).map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}