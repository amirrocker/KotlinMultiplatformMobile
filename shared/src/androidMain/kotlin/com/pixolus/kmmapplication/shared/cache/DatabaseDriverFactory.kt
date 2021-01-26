package com.pixolus.kmmapplication.shared.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {

    private val DATABASE_NAME = "test.db"
    actual fun createDriver():SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, DATABASE_NAME)
    }

}