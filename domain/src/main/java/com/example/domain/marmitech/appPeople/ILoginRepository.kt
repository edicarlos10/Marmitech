package com.example.domain.marmitech.appPeople

import io.reactivex.Completable
import io.reactivex.Single

interface ILoginRepository {
    fun getFiscal(matricula: Long, turma: Long): Single<Fiscal>
    fun getAllTurmas(): Single<List<Turma>>
    fun getFiscalSaved(matricula: Long, turma: Long): Single<FiscalSaved>
    fun insertTurma(turma: Turma): Completable
    fun insertFiscalSaved(fiscal: FiscalSaved): Completable
    fun insertFiscal(fiscal: Fiscal): Completable
}