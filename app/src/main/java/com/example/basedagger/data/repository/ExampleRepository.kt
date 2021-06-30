package com.example.basedagger.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.basedagger.data.datasource.ExamplePagingSource
import com.example.basedagger.data.model.Example
import com.example.basedagger.data.source.dao.ExampleDao
import com.example.basedagger.data.source.endpoint.ExampleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleRepository @Inject constructor(
    private val exampleDao: ExampleDao,
    private val movieApi: ExampleApi
){
    suspend fun addToFavorite(example: Example.Data) = exampleDao.addExample(example)
    fun getFavoriteMovies() = exampleDao.getAllExample()
    suspend fun checkMovie(id: String) = exampleDao.checkExample(id)
    suspend fun removeFromFavorite(id: String) {
        exampleDao.removeExample(id)
    }

    fun getExampleDataset() =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                maxSize = 100,
                enablePlaceholders = false,
                prefetchDistance = 10
            ),
            pagingSourceFactory = {ExamplePagingSource(movieApi)}
        ).liveData
}