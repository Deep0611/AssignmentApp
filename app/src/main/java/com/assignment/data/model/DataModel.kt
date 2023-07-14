package com.assignment.data.model

import com.assignment.presentation.room.DataEntity

data class DataModel(var joke: String?, var timeStamp: Long?) {
    fun toDataEntity(): DataEntity {
        val dataEntity = DataEntity()
        dataEntity.joke = joke
        dataEntity.timeStamp = timeStamp
        return dataEntity
    }
}