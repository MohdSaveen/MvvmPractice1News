package com.example.mvvmpractice1news.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDataFromAPI(result: NewEntity)

    @Query("select * from news_db")
    fun getResponseFromDb() : LiveData<List<NewEntity>>

    @Query("delete from news_db")
    fun deleteAllDataFromDB()
}