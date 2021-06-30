package com.example.basedagger.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basedagger.data.model.Photos

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExample(listPhoto: MutableList<Photos.Data>)

    @Query("SELECT * FROM photos_table")
    fun getAllExample(): LiveData<MutableList<Photos.Data>>

    @Query("SELECT * FROM photos_table WHERE title LIKE :title")
    fun getPhotoWith(title: String): LiveData<MutableList<Photos.Data>>

    @Query("SELECT count(*) FROM photos_table WHERE photos_table.id = :id")
    suspend fun checkExample(id: String): Int

    @Query("DELETE FROM photos_table WHERE photos_table.id = :id")
    suspend fun removeExample(id: String): Int
}