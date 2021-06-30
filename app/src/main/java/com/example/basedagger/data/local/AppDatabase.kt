package com.example.basedagger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basedagger.data.model.Photos
import com.example.basedagger.data.source.dao.PhotosDao

@Database(
    entities = [Photos.Data::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPhotosDao(): PhotosDao
}