//package com.kirillkuznetsov.firstapplication.data.persistent.db
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.Query
//import com.kirillkuznetsov.firstapplication.entity.Post
//
//@Dao
//interface PostDao {
//
//    @Insert
//    fun insert(post: Post): Int
//
//    @Query("SELECT * FROM post")
//    fun getAll(): List<Post>
//
//    @Query("SELECT * FROM post WHERE id=:id")
//    fun getById(id: Long): Post
//
//    @Query("SELECT * FROM post WHERE title LIKE '%' || :textQuery|| '%' OR text LIKE '%' || :textQuery|| '%'")
//    fun searchByTextQuery(textQuery: String): List<Post>
//
//    @Delete
//    fun delete(post: Post): Int
//}