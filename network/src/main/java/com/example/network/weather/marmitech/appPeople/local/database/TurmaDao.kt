package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface TurmaDao {
    @Query("SELECT * FROM turma ORDER BY codigo ASC")
    fun getAllTurma(): List<TurmaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg turma: TurmaEntity)
}