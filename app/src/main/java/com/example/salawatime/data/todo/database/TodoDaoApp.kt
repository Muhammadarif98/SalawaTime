package com.example.salawatime.data.todo.database

import androidx.room.*
import com.example.salawatime.data.todo.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
@Dao
interface TodoDaoApp {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Task>>
    fun getAllTasksDistinctUntilChanged() = getAllTasks().distinctUntilChanged()


}