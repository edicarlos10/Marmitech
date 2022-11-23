package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Error
import com.example.domain.marmitech.base.ThrowableBase
import com.example.network.weather.marmitech.appPeople.local.database.ApontamentoEntity
import com.example.network.weather.marmitech.database.AppDatabase
import io.reactivex.Completable
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

    override fun getAllApontamento(): Single<List<Apontamento>> {
        return Single.create {
            appDatabase.apontamentoDao().getAllApontamento()
                ?.let { apontamento ->
                    it.onSuccess(apontamento.map { it.toApontamento() })
                } ?: it.onError(ThrowableBase(Error.GENERIC_ERROR))
        }
    }

    override fun insertApontamento(apontamento: Apontamento): Completable {
        return Completable.create {
            appDatabase.apontamentoDao().insert(ApontamentoEntity.fromApontamento(apontamento))
            it.onComplete()
        }
    }

    override fun updateApontamento(apontamento: Apontamento): Completable {
        return Completable.create {
            appDatabase.apontamentoDao().updateApontamento(ApontamentoEntity.fromApontamento(apontamento))
            it.onComplete()
        }
    }
}