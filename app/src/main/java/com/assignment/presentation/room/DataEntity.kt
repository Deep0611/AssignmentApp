package com.assignment.presentation.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.data.model.DataModel

@Entity
class DataEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var joke: String? = null
    var timeStamp: Long? = null

    fun toDataModel(): DataModel {
        return DataModel(joke, timeStamp)
    }
}