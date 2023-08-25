package com.example.salawatime.presentation.ui.screen.reminder


import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salawatime.data.reminder.ReminderDatabase
import com.example.salawatime.data.reminder.repo.reminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(private val repository: reminderRepository) : ViewModel(){


    val listReminders : Flow<List<ReminderDatabase>> = flow {
        repository.getAllReminders().collect {
            emit(it)
        }
    }
    fun getReminders(reminder: ReminderDatabase){
        viewModelScope.launch(Dispatchers.IO){
            repository.getReminders(reminder)
        }
    }
    fun updateReminder(reminderDatabase: ReminderDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReminder(reminderDatabase)
        }
    }

    fun updateSwitchState(id: Int, isSwitchOn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSwitchState(id, isSwitchOn)
        }
    }

    private fun deleteReminder(reminder: ReminderDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteReminder(reminder)
        }
    }

 //   private val switchStateMap = mutableStateMapOf<Int, Boolean>()

//    fun getAllReminders(): Flow<List<ReminderDatabase>> = flow {
//        repository.getAllReminders().collect { reminders ->
//            // Сохраните состояния переключателей в switchStateMap
//            reminders.forEach { reminder ->
//                switchStateMap[reminder.id] = reminder.isSwitchOn
//            }
//            emit(reminders)
//        }
//    }

}