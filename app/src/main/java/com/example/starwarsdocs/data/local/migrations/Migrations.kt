package com.example.starwarsdocs.data.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.starwardocs.BuildConfig

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // SQL command to create the `PlanetData` table
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS `${BuildConfig.DB_PLANET_NAME}` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `climate` TEXT NOT NULL,
                `create` TEXT NOT NULL,
                `diameter` TEXT NOT NULL,
                `edited` TEXT NOT NULL,
                `films` TEXT NOT NULL,
                `gravity` TEXT NOT NULL,
                `name` TEXT NOT NULL,
                `orbital_period` TEXT NOT NULL,
                `population` TEXT NOT NULL,
                `residents` TEXT NOT NULL,
                `rotation_period` TEXT NOT NULL,
                `surface_water` TEXT NOT NULL,
                `url` TEXT NOT NULL,
                `terrain` TEXT NOT NULL
            )
        """)

            // SQL command to create the `StarShipData` table
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS `${BuildConfig.DB_STARSHIP_NAME}` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `MGLT` TEXT NOT NULL,
                `cargo_capacity` TEXT NOT NULL,
                `consumables` TEXT NOT NULL,
                `cost_in_credits` TEXT NOT NULL,
                `created` TEXT NOT NULL,
                `crew` TEXT NOT NULL,
                `edited` TEXT NOT NULL,
                `films` TEXT NOT NULL,
                `hyperdrive_rating` TEXT NOT NULL,
                `length` TEXT NOT NULL,
                `manufacturer` TEXT NOT NULL,
                `max_atmosphering_speed` TEXT NOT NULL,
                `model` TEXT NOT NULL,
                `name` TEXT NOT NULL,
                `passengers` TEXT NOT NULL,
                `pilots` TEXT NOT NULL,
                `starship_class` TEXT NOT NULL,
                `url` TEXT NOT NULL
            )
        """)
        }
    }

}