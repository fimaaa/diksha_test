package com.example.basedagger.data.repository

import com.example.basedagger.data.model.Photos
import com.example.basedagger.data.source.dao.PhotosDao
import com.example.basedagger.data.source.endpoint.PhotosApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(
    private val photosDao: PhotosDao,
    private val photosApi: PhotosApi
){
    fun getAllPhotos() = photosDao.getAllExample()

    fun getPhotosWith(title: String) = photosDao.getPhotoWith("%$title%")

    suspend fun saveAllPhotos(listPhotos: MutableList<Photos.Data>) = photosDao.addExample(listPhotos)

    suspend fun fetchAllPhotos() = photosApi.fetchPhotosAsync()

//    suspend fun addToFavorite(example: MutableList<Photos.Data>) = exampleDao.addExample(example)
//    fun getFavoriteMovies() = exampleDao.getAllExample()
//    suspend fun checkMovie(id: String) = exampleDao.checkExample(id)
//    suspend fun removeFromFavorite(id: String) {
//        exampleDao.removeExample(id)
//    }
}