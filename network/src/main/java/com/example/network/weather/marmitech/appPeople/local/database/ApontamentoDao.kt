package com.example.network.weather.marmitech.appPeople.local.database

import androidx.room.*

@Dao
interface ApontamentoDao {
    @Query("SELECT * FROM apontamento ORDER BY matricula DESC")
    fun getAllApontamento(): List<ApontamentoEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg apontamento: ApontamentoEntity)
}