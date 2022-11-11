package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.base.Error
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Single

class LoginLocalData(
    private val appDatabase: AppDatabase
) : ILoginLocalData {

    override fun getFiscal(matricula: Long?, turma: Long?): Single<Fiscal> {
        return Single.create {
            appDatabase.fiscalDao().getFiscal(matricula ?: 0, turma ?: 0)?.let { fiscal ->
                it.onSuccess(fiscal.toFiscal())
            } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }

    override fun getAllTurmas(): Single<List<Turma>> {
        return Single.create {
            appDatabase.turmaDao().getAllTurma()?.let { turmaList ->
                it.onSuccess(turmaList.map { turma ->
                    turma.toTurma()
                })
            } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }
}