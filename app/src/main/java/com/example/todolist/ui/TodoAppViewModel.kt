package com.example.todolist.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todolist.model.Task

class TodoAppViewModel: ViewModel() {
    private val _tasks = mutableStateListOf<Task>(
        Task("1", "Makan"),
        Task("2", "Tidur", "Istirahat tidur siang"),
        Task("3", "Kerja"),
    )

    val tasks: List<Task> get() = _tasks

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun deleteTask(taskId: String?) {
        _tasks.removeAll{it.id == taskId}
    }

    fun getTaskById(taskId: String?): Task? {
        return _tasks.find { it.id == taskId }
    }
}