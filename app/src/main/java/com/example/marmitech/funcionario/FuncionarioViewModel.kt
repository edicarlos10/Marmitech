package com.example.marmitech.funcionario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.domain.marmitech.appPeople.usecase.*
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.util.ISchedulerProvider
import com.example.marmitech.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class FuncionarioViewModel(
    scheduler: ISchedulerProvider,
    private val getFuncionarioUseCase: GetFuncionarioUseCase,
    private val getAllFuncionariosUseCase: GetAllFuncionariosUseCase
) : BaseViewModel(scheduler) {

    private val _error = MutableLiveData<Event.Error?>()
    val error: LiveData<Event.Error?>
        get() = _error

    private val _loading = MutableLiveData<Boolean?>()
    val loading: LiveData<Boolean?>
        get() = _loading

    private val _funcionario = MutableLiveData<List<Funcionario>?>()
    val funcionario: LiveData<List<Funcionario>?>
        get() = _funcionario

    private val _allFuncionario = MutableLiveData<List<Funcionario>?>()
    val allFuncionario: LiveData<List<Funcionario>?>
        get() = _allFuncionario


  //  fun getFuncionario(matricula: Long, turma: Long) {
//        getFuncionarioUseCase.execute(matricula, turma)
//            .subscribeOn(scheduler.backgroundThread())
//            .observeOn(scheduler.mainThread())
         //   .subscribe {
       //         _loading.value = it.isLoading()
     //           when (it) {
    //                is Event.Data<List<Funcionario>> -> {
  //                      _funcionario.value = it.data
      //              }
    //                is Event.Error -> {
   //                     _error.value = it
        //            }
      //              else -> Unit
    //            }
  //          }.addTo(disposables)
//    }

  //  fun getAllFuncionario(turma: Long) {
 //       getAllFuncionariosUseCase.execute(turma)
   //         .subscribeOn(scheduler.backgroundThread())
     //       .observeOn(scheduler.mainThread())
       //     .subscribe {
         //       _loading.value = it.isLoading()
           //     when (it) {
             //       is Event.Data<List<Funcionario>> -> {
               //         _allFuncionario.value = it.data
                 //   }
                   // is Event.Error -> {
                     //   _error.value = it
                    //}
                    //else -> Unit
               // }
            //}.addTo(disposables)
    //}
}