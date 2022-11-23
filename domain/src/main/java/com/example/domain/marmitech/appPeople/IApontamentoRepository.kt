package com.example.domain.marmitech.appPeople

import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Funcionario
import io.reactivex.Completable
import io.reactivex.Single

interface IApontamentoRepository {
    fun getAllFuncionario(turma: Long): Single<List<Funcionario>>
    fun getFuncionario(matricula: Long, turma: Long): Single<List<Funcionario>>
    fun getAllApontamento(): Single<List<Apontamento>>
    fun insertApontamento(apontamento: Apontamento): Completable
    fun updateApontamento(apontamento: Apontamento): Completable
}