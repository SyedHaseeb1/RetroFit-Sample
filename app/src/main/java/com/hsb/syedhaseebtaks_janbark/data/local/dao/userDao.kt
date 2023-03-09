package com.hsb.syedhaseebtaks_janbark.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hsb.syedhaseebtaks_janbark.data.models.Movies

@Dao
interface userDao {
    @Insert
    fun insert(node: Movies)

    @Query("SELECT * FROM movies_tbl")
    fun getMovies(): LiveData<List<Movies>>

    @Delete
    suspend fun delete(node: Movies)

    @Update
    suspend fun update(node: Movies)
}