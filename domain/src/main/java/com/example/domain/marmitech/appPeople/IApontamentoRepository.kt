package com.example.domain.marmitech.appPeople

import com.example.domain.marmitech.appPeople.model.Funcionario
import io.reactivex.Single

interface IApontamentoRepository {
    fun getAllFuncionario(turma: Long): Single<List<Funcionario>>
    fun getFuncionario(matricula: Long, turma: Long): Single<List<Funcionario>>
}