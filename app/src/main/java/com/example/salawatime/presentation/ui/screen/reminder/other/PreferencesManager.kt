package com.example.salawatime.presentation.ui.screen.reminder.other

import android.content.Context
import android.content.SharedPreferences
import com.example.salawatime.presentation.PREFS_NAME


fun getSwitchState(context: Context, switchKey: String): Boolean {
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(switchKey, false)
}

fun saveSwitchState(context: Context, switchKey: String, state: Boolean) {
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(switchKey, state).apply()
}

fun saveSelectedText(sharedPreferences: SharedPreferences, selectedText: String) {
    with(sharedPreferences.edit()) {
        putString("selectedText", selectedText)
        apply()
    }
}