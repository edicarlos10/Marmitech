package com.example.domain.marmitech.appPeople

data class Fiscal (
    var matricula: Long,
    var nome: String? = null,
    var turma: Int? = null,
    var senha: String? = null
)