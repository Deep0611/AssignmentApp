package com.assignment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.interactor.LocalUseCase
import com.assignment.data.interactor.RemoteUseCase
import com.assignment.data.interactor.SaveLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(
    private val getLocalUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase,
    private val saveLocalUseCase: SaveLocalUseCase
) : ViewModel() {

    suspend fun getJokesFromDB() = getLocalUseCase.invoke()

    fun getRemoteJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = remoteUseCase.invoke()
                if (response.isSuccessful) {
                    response.body()?.let { dataModel ->
                        dataModel.timeStamp = System.currentTimeMillis()
                        val id = saveLocalUseCase.invoke(dataModel)
                        Log.d(">>>>>", "Inserted id ::$id")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}