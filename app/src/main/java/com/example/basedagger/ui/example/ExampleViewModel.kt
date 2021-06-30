package com.example.basedagger.ui.example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.basedagger.data.base.Resource
import com.example.basedagger.data.model.Photos
import com.example.basedagger.data.repository.PhotosRepository
import com.example.basedagger.utill.ErrorUtils
import com.example.basedagger.utill.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: PhotosRepository
): ViewModel() {
    val search = MutableLiveData<String>()
    val listPhotos = search.map {
        repository.getPhotosWith(it)
    }

    private val _dataPhotos = MutableLiveData<Resource<MutableList<Photos.Data>>>()
    val dataPhotos: MutableLiveData<Resource<MutableList<Photos.Data>>>
        get() = _dataPhotos

    fun fetchDataPhotos() {
        _dataPhotos.value = Resource.loading()
        safeApiCall({
            repository.fetchAllPhotos().also { response ->
                if(response.size > 0) {
                    _dataPhotos.postValue(
                        Resource.success(response)
                    )
                    repository.saveAllPhotos(response)
                } else {
                    _dataPhotos.postValue(
                        Resource.error(null, HttpURLConnection.HTTP_NO_CONTENT)
                    )
                }
            }
        }, {
            _dataPhotos.postValue(
                Resource.error(
                    ErrorUtils.getErrorThrowableMsg(it),
                    ErrorUtils.getErrorThrowableCode(it)
                )
            )
        })
    }
}