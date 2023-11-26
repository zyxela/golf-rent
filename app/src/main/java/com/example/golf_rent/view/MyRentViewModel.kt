package com.example.golf_rent.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.repository.HistoryRepository
import kotlinx.coroutines.launch

class MyRentViewModel(private val historyRepository: HistoryRepository) : ViewModel() {
    val fields = MutableLiveData<List<AvailableFields>>()

    fun getMyRent(context:Context) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        viewModelScope.launch {
            fields.value = historyRepository.fetchHistoryData(sharedPreferences.getInt("USER_ID", 0))
        }
    }
}