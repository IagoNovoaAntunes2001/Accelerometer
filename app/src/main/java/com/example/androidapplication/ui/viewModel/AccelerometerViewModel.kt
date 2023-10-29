package com.example.androidapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplication.R
import com.example.androidapplication.domain.converter.AccelerometerDataConverter
import com.example.androidapplication.domain.repository.AccelerometerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccelerometerViewModel(
    private val accelerometerRepository: AccelerometerRepository,
    private val converter: AccelerometerDataConverter
) : ViewModel() {

    private val _isAvailable = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isAvailable: LiveData<Boolean>
        get() = _isAvailable

    val errorMessage = MutableLiveData<String>()

    fun sendAccelerometer(x: String, y: String, xy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = accelerometerRepository.postAccelerometer(x, y, xy)
            withContext(Dispatchers.Main) {
                response.body()?.let {
                    if (!converter.convert(it).isSuccessFull) {
                        onError()
                    }
                }
            }
        }
    }

    private fun onError() {
        errorMessage.value = "There was an error"
    }

    fun getButtonStartText() = when (isAvailable.value) {
        false -> R.string.title_start
        else -> R.string.title_top
    }

    fun setIsAvailable() {
        _isAvailable.value = !(isAvailable.value ?: false)
    }
}