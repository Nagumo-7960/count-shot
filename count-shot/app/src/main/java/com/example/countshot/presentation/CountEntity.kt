package com.example.countshot.presentation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "count_table")
data class CountEntity(
    @PrimaryKey val id: Int = 0, // 固定IDとして0を使用
    val count: Int
)
