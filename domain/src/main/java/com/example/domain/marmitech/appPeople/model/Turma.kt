package com.example.domain.marmitech.appPeople.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Turma(
    var codigo: Long? = null
) : Parcelable