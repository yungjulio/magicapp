package com.example.magicapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CardEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create new table with the new schema
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS CardEntity_new (
                        name TEXT NOT NULL PRIMARY KEY,
                        imageUrl TEXT,
                        type TEXT NOT NULL,
                        rarity TEXT NOT NULL,
                        text TEXT,
                        power TEXT,
                        toughness TEXT,
                        legality TEXT
                    )
                    """.trimIndent()
                )

                // Copy the data from the old table to the new table
                database.execSQL(
                    """
                    INSERT INTO CardEntity_new (name, imageUrl, type, rarity, text, power, toughness)
                    SELECT name, imageUrl, type, rarity, text, power, toughness
                    FROM CardEntity
                    """.trimIndent()
                )

                // Drop the old table
                database.execSQL("DROP TABLE CardEntity")

                // Rename the new table to the old table's name
                database.execSQL("ALTER TABLE CardEntity_new RENAME TO CardEntity")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "card_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration() // Optional: Use only if data loss is acceptable
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
