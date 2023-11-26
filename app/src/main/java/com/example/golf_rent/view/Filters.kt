package com.example.golf_rent.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filters() {
    var field by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom
    ) {

        Card {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = field, onValueChange = { field = it })
                TextField(value = date, onValueChange = { date = it })
                TextField(value = time, onValueChange = { time = it })
            }
        }
    }


}