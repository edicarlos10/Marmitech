package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Error
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Single

class ApontamentoLocalData(
    private val appDatabase: AppDatabase
) : IApontamentoLocalData {
    override fun getAllFuncionario(turma: Long?): Single<List<Funcionario>> {
        return Single.create {
            appDatabase.funcionarioDao().getAllFuncionario(turma ?: 0)
                ?.let { funcionario ->
                    it.onSuccess(funcionario.map { it.toFuncionario() })
                } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }

    override fun getFuncionario(matricula: Long?, turma: Long?): Single<List<Funcionario>> {
        return Single.create {
            appDatabase.funcionarioDao().getFuncionario(matricula ?: 0, turma ?: 0)
                ?.let { funcionario ->
                    it.onSuccess(funcionario.map { it.toFuncionario() })
                } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }

}