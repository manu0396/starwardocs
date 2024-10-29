package com.example.starwarsdocs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.data.local.models.PlanetData
import com.example.starwarsdocs.data.local.models.StarShipData

@TypeConverters(CustomTypeConverter::class)
@Database(
    entities = [PeopleData::class, PlanetData::class, StarShipData::class],
    version = 2,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dao(): StarWarsDao

}