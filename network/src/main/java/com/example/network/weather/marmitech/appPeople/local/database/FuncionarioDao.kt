package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface FuncionarioDao {
    @Query("SELECT * FROM funcionario WHERE turma = :turma")
    fun getAllFuncionario(turma: Long): List<FuncionarioEntity>?

    @Query("SELECT * FROM funcionario WHERE matricula = :matricula AND turma = :turma")
    fun getFuncionario(matricula: Long, turma: Long): List<FuncionarioEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg funcionario: FuncionarioEntity)
}