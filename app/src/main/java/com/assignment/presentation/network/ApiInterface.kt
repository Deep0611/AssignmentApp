package com.assignment.presentation.network

import com.assignment.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api?format=json")
    suspend fun getData(): Response<DataModel>
}