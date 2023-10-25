package com.example.androidapplication.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplication.data.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun sendAccelerometer(x: String, y: String, xy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.postAccelerometer(x, y, xy)
            withContext(Dispatchers.Main) {
                if (!response.isSuccessful) {
                    onError("There was an error")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
    }
}