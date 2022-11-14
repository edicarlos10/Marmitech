package com.example.domain.marmitech.appPeople.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fiscal (
    var matricula: Long? = null,
    var nome: String? = null,
    var turma: Int? = null,
    var senha: String? = null
): Parcelable