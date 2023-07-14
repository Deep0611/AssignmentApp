package com.assignment.data.repository

import com.assignment.data.model.DataModel

class DataRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getDataFromDB() = localDataSource.getDataFromDB()
    suspend fun getData() = remoteDataSource.getData()
    suspend fun saveDataIntoDB(dataModel: DataModel) =
        localDataSource.insertDataIntoDB(dataModel.toDataEntity())
}