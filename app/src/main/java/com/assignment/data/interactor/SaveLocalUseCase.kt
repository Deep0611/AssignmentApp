package com.assignment.data.interactor

import com.assignment.data.model.DataModel
import com.assignment.data.repository.DataRepository

class SaveLocalUseCase(private val dataRepository: DataRepository) {
    suspend fun invoke(dataModel: DataModel) = dataRepository.saveDataIntoDB(dataModel)
}