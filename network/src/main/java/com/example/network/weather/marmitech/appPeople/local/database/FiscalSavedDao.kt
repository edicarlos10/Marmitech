package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface FiscalSavedDao {

    @Query("SELECT * FROM fiscal_saved WHERE  matricula = :matricula AND turma = :turma")
    fun getFiscalSaved(matricula: Long, turma: Long): FiscalSavedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fiscalSaved: FiscalSavedEntity)
}