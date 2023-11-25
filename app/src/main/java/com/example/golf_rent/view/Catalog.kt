package com.example.golf_rent.view

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.golf_rent.data.Catalog.getCatalog
import com.example.golf_rent.entities.AvailableFields

@Composable
fun Catalog() {

    var fields by remember {
        mutableStateOf<List<AvailableFields>>(listOf<AvailableFields>())
    }
    LaunchedEffect(Unit) {
        fields = getCatalog()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                                // isDialogShowed = true
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
                .height(650.dp)
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
                            Button(onClick = { }) {
                                Text(text = "Забронировать")
                            }
                        }
                    }
                }
            }
        }
        Button(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "Фильтры")
        }
    }

}