package com.assignment.presentation.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dataEntity: DataEntity):Long

    @Delete
    fun delete(dataEntity: DataEntity)

    @Query("select * from DataEntity ORDER BY timestamp DESC limit 10")
    fun getAll(): LiveData<List<DataEntity>>
}