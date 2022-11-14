package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.domain.marmitech.appPeople.FiscalSaved
import java.io.Serializable

@Entity(tableName = "fiscal_saved")
data class FiscalSavedEntity(
    @ColumnInfo(name = "matricula") var matricula: Long? = null,
    @ColumnInfo(name = "nome") var nome: String? = null,
    @ColumnInfo(name = "turma") var turma: Int? = null,
) : Serializable {
    fun toFiscalSaved(): FiscalSaved {
        return FiscalSaved(
            matricula = matricula,
            nome = nome,
            turma = turma,
        )
    }

    companion object {
        fun fromFiscalSaved(fiscalSaved: FiscalSaved): FiscalSavedEntity {
            return FiscalSavedEntity(
                fiscalSaved.matricula,
                fiscalSaved.nome,
                fiscalSaved.turma
            )
        }
    }
}