package com.example.golf_rent.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.golf_rent.AdminPanelViewModel
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field
import org.koin.androidx.compose.getViewModel

@Composable
fun AdminPanel() {
    val viewModel: AdminPanelViewModel = getViewModel()

    val fields by viewModel.fields.observeAsState(emptyList())
    viewModel.getFields()

    val aFields by viewModel.aFields.observeAsState(emptyList())
    viewModel.getAFields()

    var fieldsList by remember {
        mutableStateOf(false)
    }
    if (fieldsList)
        FieldList(fields, viewModel)

    var aFieldsList by remember {
        mutableStateOf(false)
    }

    if (aFieldsList)
        AFieldsList(aFields, viewModel)
    Column(
        modifier = Modifier.width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier.width(300.dp),
            onClick = {
                aFieldsList = !aFieldsList
            }) {
            Text(text = "Добавить/убрать свободное поле")
        }

        Button(
            modifier = Modifier.width(300.dp),
            onClick = {
                fieldsList = !fieldsList
            }) {
            Text(text = "Добавить/убрать поле")
        }

    }
}


@Composable
fun FieldList(list: List<Field>, viewModel: AdminPanelViewModel) {
    Column {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(610.dp)
        ) {
            items(list.size) { i ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp), horizontalAlignment = Alignment.End
                    ) {
                        Text(text = list[i].name)

                        Button(onClick = {
                            viewModel.removeField(list[i].id)
                        }) {
                            Text(text = "Убрать")
                        }

                    }
                }
            }

        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

        }) {
            Text(text = "Добавить")
        }
    }
}

@Composable
fun AFieldsList(fields: List<AvailableFields>, viewModel: AdminPanelViewModel) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(550.dp)
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
                                viewModel.removeAField(fields[i].id)
                            }) {
                                Text(text = "Убрать")
                            }
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

        }) {
            Text(text = "Добавить")
        }
    }
}
