package com.example.starwarsdocs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwardocs.BuildConfig.DB_PEOPLE_NAME
import com.example.starwarsdocs.data.local.models.PeopleData

@Dao
interface StarWarsDao {

    @Query("SELECT * FROM $DB_PEOPLE_NAME")
    fun getAllLocalCharacters(): List<PeopleData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeopleData(data: PeopleData)
}