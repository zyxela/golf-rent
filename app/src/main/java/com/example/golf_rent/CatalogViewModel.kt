package com.example.golf_rent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.repository.CatalogRepository

import kotlinx.coroutines.launch


class CatalogViewModel(
    private val repository: CatalogRepository
) : ViewModel() {
    val fields = MutableLiveData<List<AvailableFields>>()

    fun fetchData() {
        viewModelScope.launch {
            fields.value = repository.fetchData()
        }
    }

    fun sortByName(){

    }

}