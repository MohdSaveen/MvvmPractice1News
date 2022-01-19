package com.example.mvvmpractice1news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpractice1news.model.local.Dao
import com.example.mvvmpractice1news.model.local.NewEntity
import com.example.mvvmpractice1news.model.local.ResponseDTO
import com.example.mvvmpractice1news.model.local.remote.api.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.ls.LSInput

class DataRepository(private val userApi: ApiService,private val dao: Dao) {

    private val userLiveData = MutableLiveData<ResponseDTO>()

    val user:LiveData<ResponseDTO> = userLiveData

    suspend fun getData() {

        val result = userApi.getData("india","cdf0c14374c54a3e9089589149d5e8aa")
        if (result?.body() != null){
            userLiveData.postValue(result.body())
        }
    }
    fun insertDataInDb(newsTable : NewEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllDataFromDB()
            dao.addDataFromAPI(newsTable)
        }
    }
    fun getDataFromDb():LiveData<List<NewEntity>>{
        return dao.getResponseFromDb()
    }

    fun deleteDataFromDb(){
        dao.deleteAllDataFromDB()
    }

}