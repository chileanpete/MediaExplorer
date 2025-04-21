package com.example.mediaexplorer.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mediaexplorer.MediaContent
import com.example.mediaexplorer.MediaViewModel
import com.example.mediaexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPageScreen(
    categoryId: Int,
    onBack: () -> Unit,
    navController: NavController,
    viewModel: MediaViewModel
) {
    val categories by viewModel.categories.collectAsState()
    val category = categories.find { it.id == categoryId }
    val no_hay_cat = stringResource(R.string.no_hay_cat)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category?.name ?: "Categoría") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addContent/$categoryId") }
            ) {
                Icon(Icons.Default.Add, "Añadir contenido")
            }
        }
    ) { padding ->
        category?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                if (it.contents.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = no_hay_cat)
                    }
                } else {
                    LazyColumn {
                        items(it.contents, key = { content -> content.id }) { content ->
                            ContentItem(content = content) {
                                navController.navigate("contentDetail/${content.id}?categoryId=$categoryId")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContentItem(content: MediaContent, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = content.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "✨".repeat(content.ranking), style = MaterialTheme.typography.bodySmall)
        }
    }
}