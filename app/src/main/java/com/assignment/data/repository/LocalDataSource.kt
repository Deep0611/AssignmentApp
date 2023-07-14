package com.assignment.data.repository

import androidx.lifecycle.LiveData
import com.assignment.presentation.room.AppDatabase
import com.assignment.presentation.room.DataEntity

class LocalDataSource(private val appDatabase: AppDatabase) {
    suspend fun getDataFromDB(): LiveData<List<DataEntity>> {
        return appDatabase.getDataDao().getAll()
    }

    suspend fun insertDataIntoDB(dataEntity: DataEntity): Long {
        return appDatabase.getDataDao().insert(dataEntity)
    }
}