package com.example.digital_journal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import com.example.digital_journal.data.DateHandler as DH
@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "post")
    val itemPost: String,
    @ColumnInfo(name = "date")
    // TODO: fix the date column to appropriately add current date
    val itemDate: String = (DH.currentDate).toString()
)