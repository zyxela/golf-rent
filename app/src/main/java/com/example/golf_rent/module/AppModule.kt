package com.example.golf_rent.module

import com.example.golf_rent.AdminPanelViewModel
import com.example.golf_rent.CatalogViewModel
import com.example.golf_rent.repository.AdminRepository
import com.example.golf_rent.repository.CatalogRepository
import com.example.golf_rent.repository.HistoryRepository
import com.example.golf_rent.repositoryIml.AdminRepositoryImpl
import com.example.golf_rent.repositoryIml.CatalogRepositoryImpl
import com.example.golf_rent.repositoryIml.HistoryRepositoryImpl
import com.example.golf_rent.view.MyRentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<CatalogRepository> { (CatalogRepositoryImpl()) }
    viewModel { CatalogViewModel(get()) }

    factory<HistoryRepository> { HistoryRepositoryImpl() }
    viewModel { MyRentViewModel(get()) }

    factory<AdminRepository> { AdminRepositoryImpl() }
    viewModel { AdminPanelViewModel(get()) }
}