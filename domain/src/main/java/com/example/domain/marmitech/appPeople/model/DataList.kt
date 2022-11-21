package com.example.domain.marmitech.appPeople.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataList(
    var fiscal: List<Fiscal>? = null,
    var turma: List<Turma>? = null,
    var funcionario: List<Funcionario>? = null
): Parcelable