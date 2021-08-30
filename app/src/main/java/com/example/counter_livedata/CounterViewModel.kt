package com.example.counter_livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private lateinit var counter: CountDownTimer

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int> get() = _seconds

    private val _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean> get() = _finished

    val _timerValue = MutableLiveData<Long>()
    val timerValue: LiveData<Long> get() = _timerValue

    fun startTimer() {

        counter = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(p0: Long) {
                val minsLeft = p0 / 1000
                _seconds.value = minsLeft.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }


    fun stopTimer() {
        counter.cancel()
    }
}