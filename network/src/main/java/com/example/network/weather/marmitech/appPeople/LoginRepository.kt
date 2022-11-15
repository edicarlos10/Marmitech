package com.example.network.weather.marmitech.appPeople

import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.ILoginRepository
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.ILoginLocalData
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Completable

class LoginRepository(
    private val localData: ILoginLocalData,
    private val appDatabase: AppDatabase
) : ILoginRepository {
    override fun getFiscal(matricula: Long, turma: Long, senha: String) = localData.getFiscal(matricula, turma, senha)
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

    override fun insertFiscalSaved(fiscal: FiscalSaved): Completable {
        return localData.insertFiscalSaved(fiscal)
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