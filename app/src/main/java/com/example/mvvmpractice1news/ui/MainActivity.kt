package com.example.mvvmpractice1news.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmpractice1news.R
import com.example.mvvmpractice1news.model.local.ArticlesDTO
import com.example.mvvmpractice1news.model.local.Dao
import com.example.mvvmpractice1news.model.local.NewEntity
import com.example.mvvmpractice1news.model.local.Newsdatabase
import com.example.mvvmpractice1news.model.local.remote.api.ApiService
import com.example.mvvmpractice1news.model.local.remote.api.Network
import com.example.mvvmpractice1news.repository.DataRepository
import com.example.mvvmpractice1news.ui.adapter.Adapter
import com.example.mvvmpractice1news.ui.adapter.OnClick
import com.example.mvvmpractice1news.viewmodel.MainViewModel
import com.example.mvvmpractice1news.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , OnClick {

    lateinit var adapter2: Adapter
    lateinit var viewModel2: MainViewModel
    lateinit var repository: DataRepository
    private var List = mutableListOf<ArticlesDTO>()
    private var newsList = mutableListOf<NewEntity>()
    lateinit var dao : Dao

    var manager : LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        dao = Newsdatabase.getNewsDatabase(this).getNews()

        val userApi = Network.getInstance().create(ApiService::class.java)
        repository= DataRepository(userApi, dao)

        val Factory = ViewModelFactory(repository)
        viewModel2 = ViewModelProviders.of(this,Factory).get(MainViewModel::class.java)

        if (isNetworkAvailable()) {
            loadApi()
        }

        viewModel2.getData().observe(this, Observer {

            newsList.addAll(it)
            setRecycle()
        })

    }

    private fun isNetworkAvailable(): Boolean {

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected

    }

    private fun loadApi() {
        viewModel2.CreateTransaction()
        viewModel2.user.observe(this,{
            List.clear()
            if (it!=null){
                CoroutineScope(Dispatchers.IO).launch {
                    insertDataToDb(it.articles as List<ArticlesDTO>)
                }
            }
            List.addAll(it.articles as MutableList<ArticlesDTO>)
            // setRecycle()
        })
    }
    private fun insertDataToDb(resultModels: List<ArticlesDTO>) {
        viewModel2.deleteDataFromDb()
        resultModels.forEach {
            val newsDb =
                it.title?.let { it1 -> it.urlToImage?.let { it2 ->
                    it.description?.let { it3 ->
                        it.publishedAt?.let { it4 ->
                            it.source?.name?.let { it5 ->
                                NewEntity(it1,
                                    it2, it3, it4, it5
                                )
                            }
                        }
                    }
                } }
            if (newsDb != null) {
                viewModel2.insertDataInDb(newsDb)
            }
        }
    }

    fun setRecycle() {
        adapter2 = Adapter(newsList, this, this)
        recycleAll.adapter = adapter2
        recycleAll.layoutManager = LinearLayoutManager(this)
    }

    override fun setOnClick(result: NewEntity) {
        val url=result.ImageUrl.toString()
        val intent= Intent(this,DetailActivity::class.java)
        intent.putExtra("name",result.Name)
        intent.putExtra("desc",result.desc)
        intent.putExtra("date",result.date)
        intent.putExtra("sorce",result.sorce)
        intent.putExtra("url",url)
        startActivity(intent)
    }
}