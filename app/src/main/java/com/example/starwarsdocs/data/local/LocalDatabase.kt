package com.example.starwarsdocs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsdocs.data.local.models.PeopleData

@TypeConverters(CustomTypeConverter::class)
@Database(
    entities = [PeopleData::class],
    version = 1,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dao(): StarWarsDao

}