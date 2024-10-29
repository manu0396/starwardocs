package com.example.starwarsdocs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwardocs.BuildConfig.DB_PEOPLE_NAME
import com.example.starwardocs.BuildConfig.DB_PLANET_NAME
import com.example.starwardocs.BuildConfig.DB_STARSHIP_NAME
import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.data.local.models.PlanetData
import com.example.starwarsdocs.data.local.models.StarShipData

@Dao
interface StarWarsDao {

    @Query("SELECT * FROM $DB_PEOPLE_NAME")
    fun getAllLocalCharacters(): List<PeopleData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeopleData(data: PeopleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanetData(data: PlanetData)

    @Query("SELECT * FROM $DB_PLANET_NAME")
    fun getAllLocalPlanets(): List<PlanetData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStarShipData(data: StarShipData)

    @Query("SELECT * FROM $DB_STARSHIP_NAME")
    fun getAllLocalStarship(): List<StarShipData>
}