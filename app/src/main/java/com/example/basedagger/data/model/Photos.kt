package com.example.basedagger.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

object Photos {
    @Entity(tableName = "photos_table")
    @Parcelize
    class Data(
        val albumId: Int,
        @PrimaryKey
        val id: Int,
        val title: String,
        val url:String,
        val thumbnailUrl: String
    ): Parcelable
}