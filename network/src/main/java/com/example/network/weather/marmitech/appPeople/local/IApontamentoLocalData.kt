package com.example.network.weather.marmitech.appPeople.local

import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Funcionario
import io.reactivex.Completable
import io.reactivex.Single

interface IApontamentoLocalData {
    fun getAllFuncionario(turma: Long?): Single<List<Funcionario>>
    fun getFuncionario(matricula: Long?, turma: Long?): Single<List<Funcionario>>
    fun getAllApontamento(): Single<List<Apontamento>>
    fun insertApontamento(apontamento: Apontamento): Completable
}