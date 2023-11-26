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

    fun sortByName(name:String){
        viewModelScope.launch {
            fields.value = repository.fetchData(name)
        }
    }

    fun sortByNameAndDate(name:String, date:String){
        viewModelScope.launch {
            fields.value = repository.fetchData(name, date)
        }
    }

    fun addField(fId:Int, uId:Int){
        viewModelScope.launch {
            repository.addRent(fId, uId)
        }
    }
}