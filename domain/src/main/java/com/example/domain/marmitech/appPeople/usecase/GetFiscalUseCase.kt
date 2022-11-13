package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetFiscalUseCase(
    val repository: ILoginRepository
) {
    fun execute(matricula: Long, turma: Long): Observable<Event<Fiscal>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getFiscal(matricula, turma)
        )
    }

    private fun getFiscal(matricula: Long, turma: Long): Observable<Event<Fiscal>> {
        return repository.getFiscal(matricula, turma).map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}