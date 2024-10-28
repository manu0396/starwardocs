package com.example.starwarsdocs.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwardocs.BuildConfig.DB_PEOPLE_NAME

@Entity(tableName = DB_PEOPLE_NAME)
data class PeopleData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("birth_year") val birth_year: String,
    @ColumnInfo("created") val created: String,
    @ColumnInfo("edited") val edited: String,
    @ColumnInfo("eye_color") val eye_color: String,
    @ColumnInfo("films") val films: List<String>,
    @ColumnInfo("gender") val gender: String,
    @ColumnInfo("hair_color") val hair_color: String,
    @ColumnInfo("height") val height: String,
    @ColumnInfo("homeworld") val homeworld:String,
    @ColumnInfo("mass") val mass:String,
    @ColumnInfo("name") val name:String,
    @ColumnInfo("skin_color") val skin_color: String,
    @ColumnInfo("species") val species: List<String>,
    @ColumnInfo("starships") val starships: List<String>,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("vehicles") val vehicles: List<String>
)