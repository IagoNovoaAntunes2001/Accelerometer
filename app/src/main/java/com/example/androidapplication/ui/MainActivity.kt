package com.example.androidapplication.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidapplication.data.MainRepository
import com.example.androidapplication.data.RetrofitService
import com.example.androidapplication.databinding.ActivityMainBinding
import com.example.androidapplication.ui.viewModel.MainViewModel
import com.example.androidapplication.ui.viewModel.MyViewModelFactory

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSensorManager: SensorManager
    private var isAvailable = false
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configManager()
        configInstances()
        setupClickListeners()
        setupObservers()
    }

    private fun configInstances() {
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        )[MainViewModel::class.java]
    }

    private fun setupClickListeners() {
        binding.buttonStart.setOnClickListener {
            binding.buttonStart.text = if (isAvailable) {
                "start"
            } else {
                "stop"
            }
            isAvailable = !isAvailable
        }
    }

    private fun setupObservers() {
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun configManager() {
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            mSensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            if (isAvailable) {
                val x = event.values[0]
                binding.textX.text = x.toString()

                val y = event.values[1]
                binding.textY.text = y.toString()

                val xy = event.values[2]
                binding.textXY.text = xy.toString()

                viewModel.sendAccelerometer(x.toString(), y.toString(), xy.toString())
            }
        }
    }

    override fun onDestroy() {
        mSensorManager.unregisterListener(this)
        super.onDestroy()
    }
}