package com.example.androidapplication.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplication.data.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()

    fun sendAccelerometer(x: String, y: String, xy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.postAccelerometer(x, y, xy)
                withContext(Dispatchers.Main) {
                    Log.i("MYTAG", response.body().toString())
                }
            } catch (e: Exception) {
                Log.i("MYTAG", e.toString())
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }
}