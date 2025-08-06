package com.example.todolist.ui.home

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.TodoAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    navController: NavController,
    viewModel: TodoAppViewModel
) {
    Scaffold (
      topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0XFF447D9B),
                titleContentColor = Color.White,
            ),
            title = { Text( text = "To-Do List") },
        )
      },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        },
    ) {
        innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(viewModel.tasks) { task ->
                    Card(
                        modifier = Modifier
                                .padding(8.dp)
                            .clickable {
                                navController.navigate("detail/${task.id}")
                            }
                    ) {
                        Text(text = task.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), viewModel = TodoAppViewModel())
}