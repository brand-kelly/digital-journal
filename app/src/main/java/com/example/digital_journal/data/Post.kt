package com.example.digital_journal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*



@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "post")
    val itemPost: String,
    @ColumnInfo(name = "date")
    val itemDate: String = "2022.02.04"
)