package com.example.basedagger.data.source.endpoint

import com.example.basedagger.data.base.BaseResponse
import com.example.basedagger.data.model.Photos
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("photos")
    suspend fun fetchPhotosAsync(): MutableList<Photos.Data>
}