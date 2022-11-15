package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface FiscalDao {
    @Query("SELECT * FROM fiscal ORDER BY matricula DESC")
    fun getAllFiscal(): List<FiscalEntity>?

    @Query("SELECT * FROM fiscal WHERE  matricula = :matricula AND turma = :turma AND senha = :senha")
    fun getFiscal(matricula: Long, turma: Long, senha: String): List<FiscalEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fiscal: FiscalEntity)
}