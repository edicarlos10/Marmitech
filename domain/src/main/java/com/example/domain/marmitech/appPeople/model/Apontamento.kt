package com.example.domain.marmitech.appPeople.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Apontamento (
    var matricula: Long? = null,
    var turma: Long? = null,
    var date: String? = null
): Parcelable