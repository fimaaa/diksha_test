package com.example.basedagger.data.source.endpoint

import com.example.basedagger.data.base.BaseResponse
import com.example.basedagger.data.model.Example
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExampleApi {
    @GET("users")
    suspend fun getExampleDataSuccess(
        @Query("since") since: Int
    ): MutableList<Example.Data>

    @GET("users")
    suspend fun getExampleDataFailed(
        @Query("since") since: Int
    ): BaseResponse<MutableList<Example.Data>>
}