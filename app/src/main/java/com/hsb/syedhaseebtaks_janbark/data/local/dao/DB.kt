package com.hsb.syedhaseebtaks_janbark.data.local.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hsb.syedhaseebtaks_janbark.data.models.Movies

@Database(
    entities = [Movies::class],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract fun userDao(): userDao

    companion object {
        @Volatile
        private var INSTANCE: DB? = null
        fun getDB(context: Context): DB {
            val tempInst = INSTANCE
            if (tempInst != null) {
                return tempInst
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "movies_tbl"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                INSTANCE = instance
                Log.e("DB", "$instance pata ni")
                return instance
            }
        }
    }
}