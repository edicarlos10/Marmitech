package com.example.network.weather.marmitech.appPeople

import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.database.AppDatabase

class LoginRepository(
    private val localData: ILoginLocalData,
    private val appDatabase: AppDatabase
) : ILoginRepository {
    override fun getFiscal(matricula: Long, turma: Long) = localData.getFiscal(matricula, turma)
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getAllTurmas() = localData.getAllTurmas()
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }
}