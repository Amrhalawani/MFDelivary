package com.aks.shagra.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aks.shagra.data.models.RequestEntity
import com.aks.shagra.data.models.User

@Database(entities = [RequestEntity::class], exportSchema = false, version = 1)
//@TypeConverters(Converters::class)
abstract class RequestDatabase : RoomDatabase() {

    abstract fun requestsDBDao(): RequestsDBDao

}