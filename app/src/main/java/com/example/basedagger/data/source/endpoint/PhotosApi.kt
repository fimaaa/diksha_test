package com.example.basedagger.data.source.endpoint

import com.example.basedagger.data.model.Photos
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    suspend fun fetchPhotosAsync(): MutableList<Photos.Data>
}