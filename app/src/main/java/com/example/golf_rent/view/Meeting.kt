package com.example.golf_rent.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.golf_rent.navigation.Screens


@Composable
fun Meeting(navHostController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.width(220.dp),
                onClick = {
                    navHostController.navigate(Screens.AuthorizationScreen.route)
                }
            ) {
                Text(text = "Войти")
            }

            OutlinedButton(
                modifier = Modifier.width(220.dp),
                onClick = {
                    navHostController.navigate(Screens.RegistrationScreen.route)
                }
            ) {
                Text(text = "Зарегистрироваться")
            }
        }
    }
}