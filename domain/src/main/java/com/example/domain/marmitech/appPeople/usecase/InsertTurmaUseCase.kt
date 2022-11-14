package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.base.Event
import io.reactivex.Observable

class InsertTurmaUseCase(
    val repository: ILoginRepository
) {
    fun execute(turma: Turma): Observable<Event<Boolean>> {
        return Observable.concat(
            Observable.just(Event.loading()),
            insertTurma(turma)
        )
    }

    private fun insertTurma(turma: Turma): Observable<Event<Boolean>> {
        return repository.insertTurma(turma).toSingle {
            Event.data(true)
        }
            .onErrorReturn {
                Event.error(it)
            }.toObservable()
    }
}