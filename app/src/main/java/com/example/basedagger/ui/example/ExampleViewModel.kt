package com.example.basedagger.ui.example

import androidx.lifecycle.ViewModel
import com.example.basedagger.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
//) : BaseViewModel<ExampleNavigator>() {
): ViewModel() {
    val exampleList = repository.getExampleDataset()
}