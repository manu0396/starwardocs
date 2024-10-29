package com.example.starwarsdocs.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwardocs.BuildConfig.DB_STARSHIP_NAME

@Entity(tableName = DB_STARSHIP_NAME)
data class StarShipData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("MGLT") val MGLT: String,
    @ColumnInfo("cargo_capacity") val cargo_capacity: String,
    @ColumnInfo("consumables") val consumables: String,
    @ColumnInfo("cost_in_credits") val cost_in_credits: String,
    @ColumnInfo("created") val created: String,
    @ColumnInfo("crew") val crew: String,
    @ColumnInfo("edited") val edited: String,
    @ColumnInfo("films") val films: List<String>,
    @ColumnInfo("hyperdrive_rating") val hyperdrive_rating: String,
    @ColumnInfo("length") val length: String,
    @ColumnInfo("manufacturer") val manufacturer: String,
    @ColumnInfo("max_atmosphering_speed") val max_atmosphering_speed: String,
    @ColumnInfo("model") val model: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("passengers") val passengers: String,
    @ColumnInfo("pilots") val pilots: String,
    @ColumnInfo("starship_class") val starship_class: String,
    @ColumnInfo("url") val url: String
)