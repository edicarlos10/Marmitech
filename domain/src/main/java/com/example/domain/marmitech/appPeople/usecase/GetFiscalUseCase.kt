package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetFiscalUseCase(
    val repository: ILoginRepository
) {
    fun execute(matricula: Long, turma: Long, senha: String): Observable<Event<List<Fiscal>>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getFiscal(matricula, turma, senha)
        )
    }

    private fun getFiscal(matricula: Long, turma: Long, senha: String): Observable<Event<List<Fiscal>>> {
        return repository.getFiscal(matricula, turma, senha).map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}