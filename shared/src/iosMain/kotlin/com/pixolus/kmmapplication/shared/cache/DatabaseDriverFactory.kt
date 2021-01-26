package com.pixolus.kmmapplication.shared.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    private val DATABASE_NAME = "test.db"
    actual fun createDriver():SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, DATABASE_NAME)
    }
}