package com.slabcode.agutierrezt.example2.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.slabcode.agutierrezt.example2.data.models.Comment

@Dao
interface CommentDao {
    @Insert
    suspend fun insertComments(comments: List<Comment>)

    @Query("SELECT * FROM Comment")
    suspend fun getAllComments(): List<Comment>
}