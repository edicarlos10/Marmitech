package com.example.network.weather.marmitech.appPeople

import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.FiscalSaved
import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Completable

class LoginRepository(
    private val localData: ILoginLocalData,
    private val appDatabase: AppDatabase
) : ILoginRepository {
    override fun getFiscal(matricula: Long, turma: Long) = localData.getFiscal(matricula, turma)
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getAllTurmas() = localData.getAllTurmas()
        .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun getFiscalSaved(matricula: Long, turma: Long) =
        localData.getFiscalSaved(matricula, turma)
            .onErrorResumeNext { ThrowableBase.parseError(it).toSingleError() }

    override fun insertTurma(turma: Turma): Completable {
        return localData.insertTurma(turma)
            .onErrorResumeNext {
                ThrowableBase.parseError(it).toCompletableError()
            }
    }

    override fun insertFiscalSaved(fiscalSaved: FiscalSaved): Completable {
        return localData.insertFiscalSaved(fiscalSaved)
            .onErrorResumeNext {
                ThrowableBase.parseError(it).toCompletableError()
            }
    }

    override fun insertFiscal(fiscal: Fiscal): Completable {
        return localData.insertFiscal(fiscal)
            .onErrorResumeNext {
                ThrowableBase.parseError(it).toCompletableError()
            }
    }
}