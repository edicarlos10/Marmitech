package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.appPeople.model.Turma
import io.reactivex.Completable
import io.reactivex.Single

interface ILoginLocalData {
    //fun getAllFuncionario(turma: Long?): Single<List<Funcionario>>
    //fun getFuncionario(matricula: Long?, turma: Long?): Single<List<Funcionario>>
    fun getFiscal(matricula: Long?, turma: Long?, senha: String?): Single<List<Fiscal>>
    fun getAllTurmas(): Single<List<Turma>>
    fun getFiscalSaved(matricula: Long?, turma: Long?): Single<FiscalSaved>
    fun insertTurma(turma: Turma): Completable
    fun insertFiscalSaved(fiscaSaved: FiscalSaved): Completable
    fun insertFiscal(fiscal: Fiscal): Completable
    fun insertFuncionario(funcionario: Funcionario): Completable
    fun deleteAllFiscalSaved(): Completable
}