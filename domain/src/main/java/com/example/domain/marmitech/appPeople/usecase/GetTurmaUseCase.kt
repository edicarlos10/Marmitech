package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetTurmaUseCase(
    val repository: ILoginRepository
) {
    fun execute(): Observable<Event<List<Turma>>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            getAllTurmas()
        )
    }

    private fun getAllTurmas(): Observable<Event<List<Turma>>> {
        return repository.getAllTurmas().map { Event.data(it) }
            .onErrorReturn { throwable ->
                (throwable as? ThrowableBase)?.let {
                    Event.error(it.type, it)
                } ?: Event.error(throwable)
            }.toObservable()
    }
}