package com.akashtanov.lighttask.lighttask.viewModels

import androidx.lifecycle.ViewModel
import com.akashtanov.lighttask.lighttask.db.entities.Task
import com.akashtanov.lighttask.lighttask.db.repositories.TaskRepository

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()
    val allTasks = repository.getAllTasks()

    fun insert(task: Task) = repository.insert(task)

    fun update(task: Task) = repository.update(task)

    fun delete(task: Task) = repository.delete(task)

    fun getTodayTasks(day: Long) = repository.getTodayTasks(day)

    fun getGroupTasks(group: String) = repository.getGroupTasks(group)
}