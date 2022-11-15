package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.model.Turma
import io.reactivex.Completable
import io.reactivex.Single

interface ILoginLocalData {
    fun getFiscal(matricula: Long?, turma: Long?, senha: String?): Single<List<Fiscal>>
    fun getAllTurmas(): Single<List<Turma>>
    fun getFiscalSaved(matricula: Long?, turma: Long?): Single<FiscalSaved>
    fun insertTurma(turma: Turma): Completable
    fun insertFiscalSaved(fiscaSaved: FiscalSaved): Completable
    fun insertFiscal(fisca: Fiscal): Completable
}