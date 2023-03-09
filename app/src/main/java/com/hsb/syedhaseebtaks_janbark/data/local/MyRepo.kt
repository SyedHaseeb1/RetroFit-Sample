package com.hsb.syedhaseebtaks_janbark.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsb.syedhaseebtaks_janbark.data.local.dao.userDao
import com.hsb.syedhaseebtaks_janbark.data.models.Movies

class MyRepo(val dao: userDao) {
    fun saveIntoDb(node: Movies) {
        dao.insert(node)
    }

    fun getMoviesFromDb(): LiveData<List<Movies>> = dao.getMovies()
    suspend fun delete(movie: Movies) {
        dao.delete(movie)
    }

    suspend fun update(updated: Movies) {
        dao.update(updated)
    }

}