package com.pixolus.kmmapplication.shared

import com.pixolus.kmmapplication.shared.cache.Database
import com.pixolus.kmmapplication.shared.cache.DatabaseDriverFactory
import com.pixolus.kmmapplication.shared.entity.RocketLaunch
import com.pixolus.kmmapplication.shared.network.RepositoryAPI

/**
 * this client class is a simple facade class over the classes created
 * and stored inside this modules packages.
 */
class RepositoryClient(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)
    private val api = RepositoryAPI()

    /**
     * Note that we are using the @Throws annotation the first time.
     * All exceptions in kotlin are unchecked while in Swift all
     * exceptions are checked. For our iOS code to realize this exception
     * a @Throws statement makes it visible for iOS.
     * See here for further information:
     *
     */
    @Throws(Exception::class)
    suspend fun getLaunches(forceSynchronization: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if(cachedLaunches.isNotEmpty() && !forceSynchronization) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }

}