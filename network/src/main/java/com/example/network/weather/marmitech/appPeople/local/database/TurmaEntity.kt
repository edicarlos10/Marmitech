package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.marmitech.appPeople.Turma
import java.io.Serializable

@Entity(tableName = "turma")
data class TurmaEntity(
    @PrimaryKey
    var codigo: Long,
) : Serializable {
    fun toTurma(): Turma {
        return Turma(
            codigo = codigo
        )
    }
}