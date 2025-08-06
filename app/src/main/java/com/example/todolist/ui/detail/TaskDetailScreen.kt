package com.example.todolist.ui.detail

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.TodoAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TodoAppViewModel,
    taskId: String?
) {
    val task = viewModel.getTaskById(taskId)

    Scaffold(
        bottomBar = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFFFE7743)
                ),
                onClick = { },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Text("Edit")
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0XFF447D9B),
                    titleContentColor = Color.White,
                ),
                title = { Text(text = task?.title ?: "") },
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    TextButton(onClick = {
                        viewModel.deleteTask(task?.id)

                        navController.popBackStack()
                    },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Text("Delete")
                    }
                }
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = task?.description ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.padding(16.dp).padding(top = 16.dp).fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen(
        navController = rememberNavController(),
        viewModel = TodoAppViewModel(),
        taskId = "2"
    )
}