package com.example.androidapplication.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapplication.databinding.ActivityMainBinding
import com.example.androidapplication.ui.viewModel.AccelerometerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SensorEventListener {
    companion object {
        private const val ZERO = 0
        private const val ONE = 1
        private const val TWO = 2
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSensorManager: SensorManager
    private var isAvailable = false

    private val viewModel: AccelerometerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configManager()
        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.buttonStart.setOnClickListener {
            binding.buttonStart.text = getString(viewModel.getButtonStartText(isAvailable))
            isAvailable = !isAvailable
        }
    }

    private fun setupObservers() {
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
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
        isTypeAccelerometer(event)
    }

    private fun isTypeAccelerometer(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) when {
            isAvailable -> setupAvailable(event)
        }
    }

    private fun setupAvailable(event: SensorEvent) {
        val x = event.values[ZERO]
        binding.textX.text = x.toString()

        val y = event.values[ONE]
        binding.textY.text = y.toString()

        val xy = event.values[TWO]
        binding.textXY.text = xy.toString()

        viewModel.sendAccelerometer(x.toString(), y.toString(), xy.toString())
    }

    override fun onDestroy() {
        mSensorManager.unregisterListener(this)
        super.onDestroy()
    }
}