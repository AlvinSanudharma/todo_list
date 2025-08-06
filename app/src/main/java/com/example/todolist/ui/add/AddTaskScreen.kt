package com.example.todolist.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.model.Task
import com.example.todolist.ui.TodoAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TodoAppViewModel
) {
    var taskTitle by remember { mutableStateOf("") }
    var isErrorTaskTitle by remember { mutableStateOf(false) }
    var taskDescription by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0XFF447D9B),
                    titleContentColor = Color.White,
                ),
                title = { Text(text = "Add Task") },
                navigationIcon = {
                    IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    ),
                        onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = {
                        isErrorTaskTitle = taskTitle.isBlank()

                        if (!isErrorTaskTitle) {
                            val newTask = Task(
                                id = (viewModel.tasks.size + 1).toString(),
                                title = taskTitle,
                                description = taskDescription,
                            )

                            viewModel.addTask(newTask)

                            navController.popBackStack()
                        }
                    }, colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White)
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) {
        innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).padding(16.dp)
            ) {
                TextField(
                    value = taskTitle,
                    label = { Text(text = "Task Title") },
                    onValueChange = { taskTitle = it
                                    isErrorTaskTitle = it.isBlank()},
                    isError = isErrorTaskTitle,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    )
                )
                if (isErrorTaskTitle) {
                    Text(
                        text = "Task title is required",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = taskDescription,
                    label = { Text(text = "Task Description") },
                    onValueChange = {taskDescription = it},
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    )
                )
            }
    }
}

@Preview
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(
        navController = rememberNavController(),
        viewModel = TodoAppViewModel(),
    )
}