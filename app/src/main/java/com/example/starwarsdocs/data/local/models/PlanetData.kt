package com.example.starwarsdocs.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwardocs.BuildConfig.DB_PLANET_NAME

@Entity(tableName = DB_PLANET_NAME)
data class PlanetData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("climate") val climate: String,
    @ColumnInfo("create") val create: String,
    @ColumnInfo("diameter") val diameter: String,
    @ColumnInfo("edited") val edited: String,
    @ColumnInfo("films") val films: List<String>,
    @ColumnInfo("gravity") val gravity: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("orbital_period") val orbital_period: String,
    @ColumnInfo("population") val population: String,
    @ColumnInfo("residents") val residents: String,
    @ColumnInfo("rotation_period") val rotation_period: String,
    @ColumnInfo("surface_water") val surface_water: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("terrain") val terrain:String
)