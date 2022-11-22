package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface FuncionarioDao {
    @Query("SELECT F.matricula, F.nome, F.turma FROM funcionario as F LEFT JOIN apontamento as A ON A.matricula == F.matricula WHERE F.turma = :turma AND A.date IS NULL GROUP BY F.matricula")
    fun getAllFuncionario(turma: Long): List<FuncionarioEntity>?

    @Query("SELECT * FROM funcionario WHERE matricula = :matricula AND turma = :turma")
    fun getFuncionario(matricula: Long, turma: Long): List<FuncionarioEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg funcionario: FuncionarioEntity)
}