package com.hsb.syedhaseebtaks_janbark.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hsb.syedhaseebtaks_janbark.data.internet.RetrofitApiCall
import com.hsb.syedhaseebtaks_janbark.data.local.MyRepo
import com.hsb.syedhaseebtaks_janbark.data.local.dao.DB
import com.hsb.syedhaseebtaks_janbark.data.models.Movies
import com.hsb.syedhaseebtaks_janbark.utils.apiKey
import com.hsb.syedhaseebtaks_janbark.utils.page
import com.hsb.syedhaseebtaks_janbark.utils.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var data: LiveData<List<Movies>>
    private var repo: MyRepo

    init {
        val dao = DB.getDB(application.applicationContext).userDao()
        repo = MyRepo(dao)
    }

    fun saveMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            val moviesList = RetrofitApiCall().searchMovies(
                apiKey, query, page
            )
            moviesList.forEach {
                repo.saveIntoDb(it)
            }
        }
    }

    fun getMovies(): LiveData<List<Movies>> = repo.getMoviesFromDb()

    fun deleteMovie(movie: Movies) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.delete(movie)
        }
    }

    fun update(updated: Movies) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(updated)
        }
    }
}

