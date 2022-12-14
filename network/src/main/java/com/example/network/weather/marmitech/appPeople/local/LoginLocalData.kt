package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.domain.marmitech.base.Error
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.database.FiscalEntity
import com.example.network.weather.marmitech.appPeople.local.database.FiscalSavedEntity
import com.example.network.weather.marmitech.appPeople.local.database.FuncionarioEntity
import com.example.network.weather.marmitech.appPeople.local.database.TurmaEntity
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single

class LoginLocalData(
    private val appDatabase: AppDatabase
) : ILoginLocalData {

    override fun getFiscal(matricula: Long?, turma: Long?, senha: String?): Single<List<Fiscal>> {
        return Single.create {
            appDatabase.fiscalDao().getFiscal(matricula ?: 0, turma ?: 0, senha ?: "")
                ?.let { fiscal ->
                    it.onSuccess(fiscal.map{it.toFiscal()})
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

    override fun getFiscalSaved(matricula: Long?, turma: Long?): Single<FiscalSaved> {
        return Single.create {
            appDatabase.fiscalSavedDao().getFiscalSaved(matricula ?: 0, turma ?: 0)
                ?.let { fiscalSaved ->
                    it.onSuccess(fiscalSaved.toFiscalSaved())
                } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }

    override fun insertTurma(turma: Turma): Completable {
        return Completable.create {
            appDatabase.turmaDao().insert(TurmaEntity.fromTurma(turma))
            it.onComplete()
        }
    }

    override fun insertFiscalSaved(fiscaSaved: FiscalSaved): Completable {
        return Completable.create {
            appDatabase.fiscalSavedDao().insert(FiscalSavedEntity.fromFiscalSaved(fiscaSaved))
            it.onComplete()
        }
    }

    override fun insertFiscal(fiscal: Fiscal): Completable {
        return Completable.create {
            appDatabase.fiscalDao().insert(FiscalEntity.fromFiscal(fiscal))
            it.onComplete()
        }
    }

    override fun insertFuncionario(funcionario: Funcionario): Completable {
        return Completable.create {
            appDatabase.funcionarioDao().insert(FuncionarioEntity.fromFuncionario(funcionario))
            it.onComplete()
        }
    }

    override fun deleteAllFiscalSaved(): Completable {
        return Completable.create {
            appDatabase.fiscalSavedDao().deleteAllFiscalSaved()
            it.onComplete()
        }
    }
}