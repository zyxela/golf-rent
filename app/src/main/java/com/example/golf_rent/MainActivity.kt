package com.example.golf_rent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.golf_rent.module.appModule
import com.example.golf_rent.navigation.ScreenGraph
import com.example.golf_rent.repository.CatalogRepository
import com.example.golf_rent.repository.HistoryRepository
import com.example.golf_rent.repositoryIml.CatalogRepositoryImpl
import com.example.golf_rent.repositoryIml.HistoryRepositoryImpl
import com.example.golf_rent.ui.theme.GolfrentTheme
import com.example.golf_rent.view.MyRentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            GolfrentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenGraph()
                }
            }
        }
    }
}

