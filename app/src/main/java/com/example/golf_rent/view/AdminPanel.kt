package com.example.golf_rent.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.window.Dialog
import com.example.golf_rent.AdminPanelViewModel
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field
import org.koin.androidx.compose.getViewModel

@Composable
fun AdminPanel() {
    val viewModel: AdminPanelViewModel = getViewModel()

    // val fields by viewModel.fields.observeAsState(emptyList())
    viewModel.getFields()

    // val aFields by viewModel.aFields.observeAsState(emptyList())
    viewModel.getAFields()

    var fieldsList by remember {
        mutableStateOf(false)
    }
    if (fieldsList)
        FieldList(viewModel)

    var aFieldsList by remember {
        mutableStateOf(false)
    }

    if (aFieldsList)
        AFieldsList(viewModel)
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
internal fun FieldList(viewModel: AdminPanelViewModel) {
    val addField by viewModel.addFieldDialog.observeAsState(false)
    val list by viewModel.fields.observeAsState()

    if (addField)
        AddFieldDialog(viewModel = viewModel)

    Column {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            items(list?.size ?: 0) { i ->
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
                        Text(text = list!![i].name)

                        Button(onClick = {
                            viewModel.removeField(list!![i].id)
                        }) {
                            Text(text = "Убрать")
                        }

                    }
                }
            }

        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                viewModel.addFieldDialog.value = !addField
            }) {
            Text(text = "Добавить")
        }
    }
}

@Composable
internal fun AFieldsList(viewModel: AdminPanelViewModel) {

    val fields by viewModel.aFields.observeAsState()
    val addAField by viewModel.addAFieldDialog.observeAsState(false)

    if (addAField)
        AddAFieldDialog(viewModel = viewModel)

    Column(
        verticalArrangement = Arrangement.Top
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            items(fields?.size ?: 0) { i ->
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
                            text = fields!![i].fieldName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )

                        Text(
                            text = fields!![i].date,
                            fontSize = 26.sp
                        )

                        Text(
                            text = fields!![i].time.dropLast(6),
                            fontSize = 24.sp
                        )
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(onClick = {
                                viewModel.removeAField(fields!![i].id)
                            }) {
                                Text(text = "Убрать")
                            }
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                viewModel.addAFieldDialog.value = true
            }) {
            Text(text = "Добавить")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddFieldDialog(viewModel: AdminPanelViewModel) {
    var fieldName by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Dialog(onDismissRequest = {

        }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card {
                    TextField(
                        value = fieldName,
                        onValueChange = {
                            fieldName = it
                        })
                    Button(onClick = {
                        viewModel.addField(fieldName)
                        viewModel.addFieldDialog.value = false
                    }) {
                        Text("Добавить")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAFieldDialog(viewModel: AdminPanelViewModel) {
    var fieldName by remember {
        mutableStateOf<Field?>(null)
    }

    var date by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Dialog(onDismissRequest = {

        }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card {

                    ExposedDropdownMenuBox(
                        expanded = isExpanded,
                        onExpandedChange = { isExpanded = it }) {

                        TextField(
                            value = fieldName?.name ?: "",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                            },
                            placeholder = {
                                Text(text = "Выберите стадион")
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = {
                                isExpanded = false
                            }
                        ) {
                            viewModel.fields.value?.forEach { s ->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = s.name)
                                    },
                                    onClick = {
                                        fieldName = s
                                        isExpanded = false
                                    }
                                )
                            }

                        }


                    }


                    TextField(
                        value = date,
                        onValueChange = {
                            date = it
                        })

                    TextField(
                        value = time,
                        onValueChange = {
                            time = it
                        })


                    Button(onClick = {
                        viewModel.addAField(
                            AvailableFields(
                                0,
                                fieldName!!.id,
                                fieldName!!.name,
                                date,
                                time
                            )
                        )
                        viewModel.addAFieldDialog.value = false
                    }) {
                        Text("Добавить")
                    }
                }
            }
        }
    }
}