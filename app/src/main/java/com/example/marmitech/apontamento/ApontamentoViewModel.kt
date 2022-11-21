package com.example.marmitech.apontamento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.appPeople.usecase.GetAllApontamentoCase
import com.example.domain.marmitech.appPeople.usecase.GetAllFuncionariosUseCase
import com.example.domain.marmitech.appPeople.usecase.GetFuncionarioUseCase
import com.example.domain.marmitech.appPeople.usecase.InsertApontamentoUseCase
import com.example.domain.marmitech.base.Event
import com.example.domain.marmitech.util.ISchedulerProvider
import com.example.marmitech.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class ApontamentoViewModel(
    scheduler: ISchedulerProvider,
    private val getFuncionarioUseCase: GetFuncionarioUseCase,
    private val getAllFuncionariosUseCase: GetAllFuncionariosUseCase,
    private val getAllApontamentoUseCase: GetAllApontamentoCase,
    private val insertApontamentoUseCase: InsertApontamentoUseCase
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

    private val _allApontamento = MutableLiveData<List<Apontamento>?>()
    val allApontamento: LiveData<List<Apontamento>?>
        get() = _allApontamento

    private val _insertApontamento = MutableLiveData<Boolean>()
    val insertApontamento: LiveData<Boolean>
        get() = _insertApontamento


    fun getFuncionario(matricula: Long, turma: Long) {
        getFuncionarioUseCase.execute(matricula, turma)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<List<Funcionario>> -> {
                        _funcionario.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun getAllFuncionario(turma: Long) {
        getAllFuncionariosUseCase.execute(turma)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<List<Funcionario>> -> {
                        _allFuncionario.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun getAllApontamento() {
        getAllApontamentoUseCase.execute()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                _loading.value = it.isLoading()
                when (it) {
                    is Event.Data<List<Apontamento>> -> {
                        _allApontamento.value = it.data
                    }
                    is Event.Error -> {
                        _error.value = it
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }

    fun insertApontamento(apontamento: Apontamento) {
        insertApontamentoUseCase.execute(apontamento).subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe { event ->
                _loading.value = event.isLoading()
                when (event) {
                    is Event.Data<Boolean> -> {
                        _insertApontamento.value = true
                    }
                    is Event.Error -> {
                        _error.value = event
                    }
                    else -> Unit
                }
            }.addTo(disposables)
    }
}