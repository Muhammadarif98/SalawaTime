package com.example.salawatime.presentation.ui.screen.todo


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salawatime.data.todo.Task
import com.example.salawatime.data.todo.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel(){


    val listTasks : Flow<List<Task>> = flow {
        repository.getAllTasks().collect {
            emit(it)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }
    private fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}