package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.marmitech.appPeople.model.Fiscal
import java.io.Serializable

@Entity(tableName = "fiscal")
data class FiscalEntity(
    @PrimaryKey var matricula: Long? = null,
    @ColumnInfo(name = "senha") var senha: String? = null,
    @ColumnInfo(name = "nome") var nome: String? = null,
    @ColumnInfo(name = "turma") var turma: Int? = null,
) : Serializable {
    fun toFiscal(): Fiscal {
        return Fiscal(
           matricula = matricula,
           nome = nome,
           senha = senha,
           turma = turma,
        )
    }

    companion object {
        fun fromFiscal(fiscal: Fiscal): FiscalEntity {
            return FiscalEntity(
                fiscal.matricula,
                fiscal.senha,
                fiscal.nome,
                fiscal.turma
            )
        }
    }
}