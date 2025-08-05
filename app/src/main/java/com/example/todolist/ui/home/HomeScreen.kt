package com.example.todolist.ui.home

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.TodoAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    viewModel: TodoAppViewModel
) {
    Scaffold (
      topBar = {
        TopAppBar(
            title = { Text( text = "Home") }
        )
      },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
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
                                // TODO
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
    HomeScreen(viewModel = TodoAppViewModel())
}