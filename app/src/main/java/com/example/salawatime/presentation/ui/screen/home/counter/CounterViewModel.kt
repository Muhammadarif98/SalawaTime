package com.example.salawatime.presentation.ui.screen.home.counter

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    var count: MutableState<Int> = mutableStateOf(0)

    fun setupSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("CounterPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        count.value = sharedPreferences.getInt("count", 0)
    }

    fun incrementCounter() {
        count.value++
        saveCounter()
    }

    fun resetCounter() {
        count.value = 0
        saveCounter()
    }

    fun decrementCounter() {
        if (count.value > 0) {
            count.value--
            saveCounter()
        }
    }

    private fun saveCounter() {
        editor.putInt("count", count.value)
        editor.apply()
    }

    override fun onCleared() {
        saveCounter()
        super.onCleared()
    }
}