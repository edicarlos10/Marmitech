package com.example.network.weather.marmitech.appPeople

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.IApontamentoLocalData
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Completable

class ApontamentoRepository(
    private val localData: IApontamentoLocalData,
    private val appDatabase: AppDatabase
) : IApontamentoRepository {
    override fun getAllFuncionario(turma: Long) = localData.getAllFuncionario(turma)
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getFuncionario(matricula: Long, turma: Long) =
        localData.getFuncionario(matricula, turma)
            .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getAllApontamento() = localData.getAllApontamento()
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun insertApontamento(apontamento: Apontamento): Completable {
        return localData.insertApontamento(apontamento)
            .onErrorResumeNext {
                ThrowableBase.parseError(it).toCompletableError()
            }
    }

    override fun updateApontamento(apontamento: Apontamento): Completable {
        return localData.updateApontamento(apontamento)
            .onErrorResumeNext {
                ThrowableBase.parseError(it).toCompletableError()
            }
    }
}