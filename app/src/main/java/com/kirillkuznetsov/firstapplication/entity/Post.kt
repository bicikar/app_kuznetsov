package com.kirillkuznetsov.firstapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Post(
    @Json(name = "id")
    @PrimaryKey
    val id: Long,

    @Json(name = "link")
    @ColumnInfo(name = "link_url")
    val linkUrl: String?,

    @Json(name = "image")
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @Json(name = "title")
    @ColumnInfo(name = "title")
    val title: String?,

    @Json(name = "text")
    @ColumnInfo(name = "text")
    val text: String?,

    @Json(name = "created_at")
    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @Json(name = "updated_at")
    @ColumnInfo(name = "updated_at")
    val updatedAt: String
)
