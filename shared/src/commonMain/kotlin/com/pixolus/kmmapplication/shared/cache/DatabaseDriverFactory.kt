package com.pixolus.kmmapplication.shared.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 * we need to provide an actual implementation for expected class.
 */
expect class DatabaseDriverFactory {

    fun createDriver():SqlDriver

}