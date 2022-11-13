package com.example.marmitech.marmitechUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.FiscalSaved
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.appPeople.usecase.GetFiscalSavedUseCase
import com.example.domain.marmitech.appPeople.usecase.GetFiscalUseCase
import com.example.domain.marmitech.appPeople.usecase.GetTurmaUseCase
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.util.ISchedulerProvider
import com.example.marmitech.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class LoginViewModel (
    scheduler: ISchedulerProvider,
    private val getFiscalUseCase: GetFiscalUseCase,
    private val getFiscalSavedUseCase: GetFiscalSavedUseCase,
    private val getTurmaUseCase: GetTurmaUseCase
) : BaseViewModel(scheduler) {

    private val _error = MutableLiveData<Event.Error?>()
    val error: LiveData<Event.Error?>
        get() = _error

    private val _loading = MutableLiveData<Boolean?>()
    val loading: LiveData<Boolean?>
        get() = _loading

    private val _fiscal = MutableLiveData<Fiscal?>()
    val fiscal: LiveData<Fiscal?>
        get() = _fiscal

    private val _fiscalSaved = MutableLiveData<FiscalSaved?>()
    val fiscalSaved: LiveData<FiscalSaved?>
        get() = _fiscalSaved

    private val _allTurma = MutableLiveData<List<Turma>?>()
    val allTurma: LiveData<List<Turma>?>
        get() = _allTurma

    fun getFiscal(matricula: Long, turma: Long) {
        getFiscalUseCase.execute(matricula, turma)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<Fiscal> -> {
                        _fiscal.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun getFiscalSaved(matricula: Long, turma: Long) {
        getFiscalSavedUseCase.execute(matricula, turma)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<FiscalSaved> -> {
                        _fiscalSaved.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun getAllTurma() {
        getTurmaUseCase.execute()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<List<Turma>> -> {
                        _allTurma.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }
}