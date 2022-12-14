package com.example.marmitech.login

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

class LoginViewModel(
    scheduler: ISchedulerProvider,
    private val getFiscalUseCase: GetFiscalUseCase,
    private val getFiscalSavedUseCase: GetFiscalSavedUseCase,
    private val insertTurmaUseCase: InsertTurmaUseCase,
    private val insertFiscalSavedUseCase: InsertFiscalSavedUseCase,
    private val insertFiscalUseCase: InsertFiscalUseCase,
    private val getTurmaUseCase: GetTurmaUseCase,
    private val removeAllFiscalSavedUseCase: RemoveAllFiscalSavedUseCase,
    private val insertFuncionarioUseCase: InsertFuncionarioUseCase
) : BaseViewModel(scheduler) {

    private val _error = MutableLiveData<Event.Error?>()
    val error: LiveData<Event.Error?>
        get() = _error

    private val _loading = MutableLiveData<Boolean?>()
    val loading: LiveData<Boolean?>
        get() = _loading

    private val _fiscal = MutableLiveData<List<Fiscal>?>()
    val fiscal: LiveData<List<Fiscal>?>
        get() = _fiscal

    private val _fiscalSaved = MutableLiveData<FiscalSaved?>()
    val fiscalSaved: LiveData<FiscalSaved?>
        get() = _fiscalSaved

    private val _allTurmas = MutableLiveData<List<Turma>?>()
    val allTurmas: LiveData<List<Turma>?>
        get() = _allTurmas

    private val _insertTurma = MutableLiveData<Boolean>()
    val insertTurma: LiveData<Boolean>
        get() = _insertTurma

    private val _insertFiscalSaved = MutableLiveData<Boolean>()
    val insertFiscalSaved: LiveData<Boolean>
        get() = _insertFiscalSaved

    private val _insertFiscal = MutableLiveData<Boolean>()
    val insertFiscal: LiveData<Boolean>
        get() = _insertFiscal

    private val _insertFuncionario = MutableLiveData<Boolean>()
    val insertFuncionario: LiveData<Boolean>
        get() = _insertFuncionario

    private val _deleteAllFiscalSaved = MutableLiveData<Boolean>()
    val deleteAllFiscalSaved: LiveData<Boolean>
        get() = _deleteAllFiscalSaved

    fun getFiscal(matricula: Long, turma: Long, senha: String) {
        getFiscalUseCase.execute(matricula, turma, senha)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<List<Fiscal>> -> {
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
                        _allTurmas.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun insertTurma(turma: Turma) {
        insertTurmaUseCase.execute(turma).subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe { event ->
                when (event) {
                    is Event.Data<Boolean> -> {
                        _insertTurma.value = true
                    }
                    is Event.Error -> {
                        _error.value = event
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun insertFiscalSaved(fiscalSaved: FiscalSaved) {
        insertFiscalSavedUseCase.execute(fiscalSaved).subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe { event ->
                when (event) {
                    is Event.Data<Boolean> -> {
                        _insertFiscalSaved.value = true
                    }
                    is Event.Error -> {
                        _error.value = event
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun insertFiscal(fiscal: Fiscal) {
        insertFiscalUseCase.execute(fiscal).subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe { event ->
                when (event) {
                    is Event.Data<Boolean> -> {
                        _insertFiscal.value = true
                    }
                    is Event.Error -> {
                        _error.value = event
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun insertFuncionario(funcionario: Funcionario) {
        insertFuncionarioUseCase.execute(funcionario).subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe { event ->
                when (event) {
                    is Event.Data<Boolean> -> {
                        _insertFuncionario.value = true
                    }
                    is Event.Error -> {
                        _error.value = event
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun deleteAllFiscalSaved() {

        removeAllFiscalSavedUseCase.execute()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data<*> -> {
                        _deleteAllFiscalSaved.value = it.data as Boolean
                    }
                    is Event.Error -> _error.value = it
                    else -> Unit
                }
            }.addTo(disposables)
    }
}