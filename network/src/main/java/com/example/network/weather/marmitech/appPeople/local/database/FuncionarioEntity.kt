package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.Funcionario
import java.io.Serializable

@Entity(tableName = "funcionario")
data class FuncionarioEntity(
    @PrimaryKey var matricula: Long? = null,
    @ColumnInfo(name = "nome") var nome: String? = null,
    @ColumnInfo(name = "turma") var turma: Int? = null,
) : Serializable {
    fun toFuncionario(): Funcionario {
        return Funcionario(
           matricula = matricula,
           nome = nome,
           turma = turma,
        )
    }

    companion object {
        fun fromFuncionario(funcionario: Funcionario): FuncionarioEntity {
            return FuncionarioEntity(
                funcionario.matricula,
                funcionario.nome,
                funcionario.turma
            )
        }
    }
}