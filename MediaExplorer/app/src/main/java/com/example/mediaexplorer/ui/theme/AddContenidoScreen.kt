package com.example.mediaexplorer.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mediaexplorer.MediaContent
import com.example.mediaexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContentScreen(
    categoryId: Int,
    onBack: () -> Unit,
    onSave: (MediaContent) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var ranking by remember { mutableStateOf(3f) }
    var error by remember { mutableStateOf<String?>(null) }
    val titulo_addcont = stringResource(R.string.titulo_addcont)
    val titulo = stringResource(R.string.titulo)
    val opinion = stringResource(R.string.opinion)
    val guardar_cont = stringResource(R.string.guardar_cont)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = titulo_addcont) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Imagen3()
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    error = null
                },
                label = { Text(text = titulo) },
                isError = error != null,
                supportingText = { error?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = opinion) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Ranking: ${ranking.toInt()} estrellas")
            Slider(
                value = ranking,
                onValueChange = { ranking = it },
                valueRange = 1f..5f,
                steps = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (title.isBlank()) {
                        error = "El título es requerido"
                    } else {
                        onSave(
                            MediaContent(
                                id = (0..1000).random(),
                                title = title,
                                description = description,
                                ranking = ranking.toInt()
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank()
            ) {
                Text(text = guardar_cont)
            }
        }
    }
}

@Composable
fun Imagen3(){
    Image(modifier = Modifier.height(height = 150.dp).width(width = 200.dp),
        painter = painterResource(id = R.drawable.contentadd),
        contentDescription = "Home Image"
    )
}