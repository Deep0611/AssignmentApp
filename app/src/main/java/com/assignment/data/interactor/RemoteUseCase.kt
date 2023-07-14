package com.assignment.data.interactor

import com.assignment.data.repository.DataRepository

class RemoteUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke() = dataRepository.getData()
}