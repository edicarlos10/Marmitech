package com.example.domain.marmitech.appPeople

import io.reactivex.Single

interface ILoginRepository {
    fun getFiscal(matricula: Long, turma: Long): Single<Fiscal>
    fun getAllTurmas(): Single<List<Turma>>
}