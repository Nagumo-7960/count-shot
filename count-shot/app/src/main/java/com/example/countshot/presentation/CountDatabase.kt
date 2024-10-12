package com.example.countshot.presentation

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountEntity::class], version = 1)
abstract class CountDatabase : RoomDatabase() {
    abstract fun countDao(): CountDao
}
