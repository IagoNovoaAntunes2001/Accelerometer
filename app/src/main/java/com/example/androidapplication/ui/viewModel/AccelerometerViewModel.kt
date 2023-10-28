package com.example.androidapplication.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplication.R
import com.example.androidapplication.domain.AccelerometerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccelerometerViewModel @Inject constructor(
    private val accelerometerRepository: AccelerometerRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun sendAccelerometer(x: String, y: String, xy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = accelerometerRepository.postAccelerometer(x, y, xy)
            withContext(Dispatchers.Main) {
                if (!response.isSuccessful) {
                    onError()
                }
            }
        }

    }

    private fun onError() {
        errorMessage.value = "There was an error"
    }

    fun getButtonStartText(available: Boolean) = when {
        available -> R.string.title_start
        else -> R.string.title_top
    }
}