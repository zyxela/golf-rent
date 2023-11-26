package com.example.golf_rent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field
import com.example.golf_rent.repository.AdminRepository
import kotlinx.coroutines.launch

class AdminPanelViewModel(private val repository: AdminRepository):ViewModel(){
    val fields = MutableLiveData<List<Field>>()
    val aFields = MutableLiveData<List<AvailableFields>>()

    fun getFields(){
        viewModelScope.launch {
            fields.value = repository.fetchFields()
        }
    }

    fun getAFields(){
        viewModelScope.launch {
            aFields.value = repository.fetchAvailableFields()
        }
    }

    fun removeField(id:Int){
        viewModelScope.launch {
            repository.removeField(id)
        }
    }

    fun removeAField(id:Int){
        viewModelScope.launch {
            repository.removeAField(id)
        }
    }

    fun addField(name:String){
        viewModelScope.launch {
            repository.addField(name)
        }
    }

    fun addAField(fields: AvailableFields){
        viewModelScope.launch{
            repository.addAField(fields)
        }
    }
}