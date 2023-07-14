package com.assignment.data.repository

import com.assignment.data.model.DataModel
import com.assignment.presentation.network.ApiInterface
import retrofit2.Response

class RemoteDataSource(private val apiInterface: ApiInterface) {
    suspend fun getData(): Response<DataModel> {
        return apiInterface.getData()
    }
}