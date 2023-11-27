package com.example.golf_rent.view

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.golf_rent.CatalogViewModel
import com.example.golf_rent.navigation.Screens
import org.koin.androidx.compose.getViewModel


@Composable
fun Catalog(navController: NavController) {

    val viewModel: CatalogViewModel = getViewModel()

    val fields by viewModel.fields.observeAsState(emptyList())

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    if (fields.isEmpty())
        viewModel.fetchData()

    var showDialog by remember {
        mutableStateOf(false)
    }

    var lcHeight by remember {
        mutableStateOf(650.dp)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var rent by remember {
            mutableStateOf(false)
        }

        if (rent)
            navController.navigate(Screens.MyRentScreen.route)

        Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.End) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(0.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Мои брони",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                rent = !rent
                            },
                        imageVector = Icons.Outlined.Book, contentDescription = ""
                    )
                }

            }

        }
        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(lcHeight)
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
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(onClick = {
                                viewModel.addField(fields[i].id, prefs.getInt("USER_ID", 0))
                            }) {
                                Text(text = "Забронировать")
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            Filters(viewModel)
            lcHeight = 500.dp
        } else {
            lcHeight = 650.dp
        }
        Button(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
            onClick = {
                showDialog = !showDialog
            }) {
            Text(text = "Фильтры")
        }


    }

}