package com.kirillkuznetsov.firstapplication.data.persistent.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kirillkuznetsov.firstapplication.entity.Post

@Database(
    entities = [
        Post::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao


}