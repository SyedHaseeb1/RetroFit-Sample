package com.hsb.syedhaseebtaks_janbark.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hsb.syedhaseebtaks_janbark.utils.ViewHolder_Simple

@Entity(tableName = "movies_tbl")
data class Movies(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var viewType: Int = ViewHolder_Simple,
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)