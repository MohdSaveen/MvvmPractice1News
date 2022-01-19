package com.example.mvvmpractice1news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpractice1news.model.local.NewEntity
import com.example.mvvmpractice1news.model.local.ResponseDTO
import com.example.mvvmpractice1news.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun CreateTransaction(){
        viewModelScope.launch {
            dataRepository.getData()
        }
    }

    val user:LiveData<ResponseDTO> = dataRepository.user

    fun insertDataInDb(itunesDbTable : NewEntity) {
        dataRepository.insertDataInDb(itunesDbTable)
    }
    fun deleteDataFromDb(){
        dataRepository.deleteDataFromDb()
    }
     fun getData(): LiveData<List<NewEntity>> {
         return dataRepository.getDataFromDb()
     }

}