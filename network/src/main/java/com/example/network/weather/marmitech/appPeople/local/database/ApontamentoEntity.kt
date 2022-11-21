package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.marmitech.appPeople.model.Apontamento
import java.io.Serializable

@Entity(tableName = "apontamento")
data class ApontamentoEntity(
    @PrimaryKey var matricula: Long? = null,
    @ColumnInfo(name = "turma") var turma: Long? = null,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "pegou") var pegou: Boolean? = null,
) : Serializable {
    fun toApontamento(): Apontamento {
        return Apontamento(
            matricula = matricula,
            turma = turma,
            date = date,
            pegou = pegou,
        )
    }

    companion object {
        fun fromApontamento(apontamento: Apontamento): ApontamentoEntity {
            return ApontamentoEntity(
                apontamento.matricula,
                apontamento.turma,
                apontamento.date,
                apontamento.pegou
            )
        }
    }
}