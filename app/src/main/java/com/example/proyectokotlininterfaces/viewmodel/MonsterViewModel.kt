package com.example.proyectokotlininterfaces.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectokotlininterfaces.model.MonsterResponse
import com.example.proyectokotlininterfaces.network.ApiService
import kotlinx.coroutines.launch

class MonsterViewModel: ViewModel() {
    var response: MonsterResponse by mutableStateOf(MonsterResponse())
    var errorMessage: String by mutableStateOf("");

    fun getMonsterList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val basicResponse = apiService.getMonsters()
                response = basicResponse
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

}