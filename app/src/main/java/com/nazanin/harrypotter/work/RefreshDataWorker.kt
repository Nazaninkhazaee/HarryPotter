package com.nazanin.harrypotter.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nazanin.harrypotter.database.dao.AppDatabase.Companion.getDatabase
import com.nazanin.harrypotter.repository.CharacterRepository

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CharacterRepository(database)
        return try {
            repository.fetchAllCharacters()
            Result.success()
        } catch (e: retrofit2.HttpException) {
            Result.retry()
        }
    }
}