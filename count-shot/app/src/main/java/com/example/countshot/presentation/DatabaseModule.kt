package com.example.countshot.presentation

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CountDatabase {
        return Room.databaseBuilder(
            context,
            CountDatabase::class.java,
            "count_database"
        ).build()
    }

    @Provides
    fun provideCountDao(database: CountDatabase): CountDao {
        return database.countDao()
    }
}
