package com.example.james_code_challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.james_code_challenge.data.database.dao.FavouriteItemDao
import com.example.james_code_challenge.data.model.FavouriteItem

@Database(entities = [FavouriteItem::class], version = 1, exportSchema = false)
@TypeConverters(DefaultTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteItemDao(): FavouriteItemDao
}