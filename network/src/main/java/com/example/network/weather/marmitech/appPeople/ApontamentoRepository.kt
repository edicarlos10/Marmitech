package com.example.network.weather.marmitech.appPeople

import com.example.domain.marmitech.appPeople.IApontamentoRepository
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.IApontamentoLocalData
import com.example.network.weather.marmitech.database.AppDatabase

class ApontamentoRepository(
    private val localData: IApontamentoLocalData,
    private val appDatabase: AppDatabase
) : IApontamentoRepository {
    override fun getAllFuncionario(turma: Long) = localData.getAllFuncionario(turma)
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getFuncionario(matricula: Long, turma: Long) =
        localData.getFuncionario(matricula, turma)
            .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }
}