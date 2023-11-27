package com.example.golf_rent.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.golf_rent.navigation.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun MyRent(navController: NavController) {
    val viewModel: MyRentViewModel = getViewModel()

    val fields by viewModel.fields.observeAsState(emptyList())

    viewModel.getMyRent(LocalContext.current)

    Column {
        Button(onClick = {
            navController.navigate(Screens.CatalogScreen.route)
        }) {
            Text(text = "Назад")
        }

        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            items(fields.size) { i ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                    ) {
                        Text(
                            text = fields[i].fieldName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )

                        Text(
                            text = fields[i].date,
                            fontSize = 26.sp
                        )

                        Text(
                            text = fields[i].time.dropLast(6),
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }

    }
}