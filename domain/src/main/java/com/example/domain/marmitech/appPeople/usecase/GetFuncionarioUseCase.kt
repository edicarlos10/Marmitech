package com.example.domain.marmitech.appPeople.usecase

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.base.ThrowableBase
import io.reactivex.Observable

class GetFuncionarioUseCase(
    val repository: ILoginRepository
) {
  //  fun execute(matricula: Long, turma: Long): Observable<Event<List<Funcionario>>> {
    //    return Observable.concat(
      //      Observable.just(Event.loading()),
        //    getFuncionario(matricula, turma)
        //)
    //}

    //private fun getFuncionario(matricula: Long, turma: Long): Observable<Event<List<Funcionario>>> {
     //   return repository.getFuncionario(matricula, turma).map { Event.data(it) }
       //     .onErrorReturn { throwable ->
         //       (throwable as? ThrowableBase)?.let {
           //         Event.error(it.type, it)
             //   } ?: Event.error(throwable)
            //}.toObservable()
    //}
}