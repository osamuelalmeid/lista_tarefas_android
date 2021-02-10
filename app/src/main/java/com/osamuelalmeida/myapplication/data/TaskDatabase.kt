package com.osamuelalmeida.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.osamuelalmeida.myapplication.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Aula de Yoga", isCompleted = true))
                dao.insert(Task("Buscar o carro no mecânico"))
                dao.insert(Task("Ir ao supermercado", isCompleted = true))
                dao.insert(Task("Buscar filhos no colégio", isImportant = true))
                dao.insert(Task("Correr na ciclovia"))
                dao.insert(Task("Preparar o jantar", isImportant = true))
            }

        }
    }
}