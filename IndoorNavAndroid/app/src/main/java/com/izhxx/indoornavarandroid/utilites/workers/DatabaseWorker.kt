package com.izhxx.indoornavarandroid.utilites.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.izhxx.indoornavarandroid.data.databases.AppDatabase
import com.izhxx.indoornavarandroid.data.databases.locationsdatabase.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val fileName = inputData.getString(FILENAME)
            if (fileName != null) {
                applicationContext.assets.open(fileName).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val itemType = object : TypeToken<List<Location>>() {}.type
                        val locationList: List<Location> = Gson().fromJson(jsonReader, itemType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.locationDao().insertLocations(locationList)

                        Result.success()
                    }
                }
            } else {
                Log.e(logTagName, "Database name is null")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(logTagJavaErrors, "Error database", e)
            Result.failure()
        }
    }

    companion object {
        private const val logTagName = "DatabaseNameError"
        private const val logTagJavaErrors = "JavaErrors"
        const val FILENAME = "FILENAME"
    }
}