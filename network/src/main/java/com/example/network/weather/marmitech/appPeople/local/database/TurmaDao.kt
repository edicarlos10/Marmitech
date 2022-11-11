package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface TurmaDao {
    @Query("SELECT * FROM turma ORDER BY codigo DESC")
    fun getAllTurma(): List<TurmaEntity>?
}