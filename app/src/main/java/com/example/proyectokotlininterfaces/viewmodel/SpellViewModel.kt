package com.example.proyectokotlininterfaces.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.proyectokotlininterfaces.model.SpellResponse
import com.example.proyectokotlininterfaces.network.ApiService
import kotlinx.coroutines.launch

class SpellViewModel: ViewModel() {
    var response: SpellResponse by mutableStateOf(SpellResponse())
    var errorMessage: String by mutableStateOf("");

    fun getSpellList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val basicResponse = apiService.getSpells()
                response = basicResponse
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

}