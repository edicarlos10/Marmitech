package com.example.network.weather.marmitech.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.network.weather.marmitech.appPeople.local.database.*

@Database(
    entities = [FiscalEntity::class, TurmaEntity::class, FiscalSavedEntity::class, FuncionarioEntity::class, ApontamentoEntity::class],
    version = 8,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fiscalDao(): FiscalDao
    abstract fun turmaDao(): TurmaDao
    abstract fun fiscalSavedDao(): FiscalSavedDao
    abstract fun funcionarioDao(): FuncionarioDao
    abstract fun apontamentoDao(): ApontamentoDao
}