package com.pixolus.kmmapplication.shared.cache

import com.pixolus.kmmapplication.shared.entity.Links
import com.pixolus.kmmapplication.shared.entity.RocketLaunch
import com.pixolus.kmmapplication.shared.entity.Rocket

/**
 * internal visibility == access only from inside shared module. module private.
 */
class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllRockets()
            dbQuery.removeAllLaunches()
        }
    }

    /**
     * The method returning a number of entities.
     * Note the immutable data creation.
     */
    fun getAllLaunches(): List<RocketLaunch> {
        return dbQuery.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    private fun mapLaunchSelecting(
        flightNumber:Long,
        missionName: String,
        launchYear: Int,
        rocketId: String,
        details: String?,
        launchSuccess: Boolean?,
        launchDateUTC: String,
        missionPatchUrl: String?,
        articleUrl: String?,
        rocket_id: String?, // TODO remove!
        name: String?,
        type: String?

    ):RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber.toInt(),
            missionName = missionName,
            launchYear = launchYear,
            details = details,
            launchDateUTC = launchDateUTC,
            launchSuccess = launchSuccess,
            rocket = Rocket(
                id = rocketId,
                name = name!!,
                type = type!!

            ),
            links = Links(
                missionPatchUrl = missionPatchUrl,
                articleUrl = articleUrl

            )
        )
    }

    internal fun createLaunches(launches:List<RocketLaunch>) {
        dbQuery.transaction {
            launches.forEach { launch ->
                val rocket = dbQuery.selectRocketById(launch.rocket.id).executeAsOneOrNull()
                if( rocket == null ) { // TODO ASAP remove null! and replace with None or something like that
                    insertRocket(launch)
                }
                insertLaunch(launch)
            }
        }
    }

    /**
     * TODO look up why no transaction is needed for insert.
     *      Would it not be important here ?
     */
    private fun insertRocket(launch:RocketLaunch) {
        dbQuery.insertRocket(
                id=launch.rocket.id,
                name = launch.rocket.name,
                type = launch.rocket.type
        )
    }

    /**
     * TODO and here?
     */
    private fun insertLaunch(launch:RocketLaunch) {
        dbQuery.insertLaunch(
                launchDateUTC = launch.launchDateUTC,
                flightNumber = launch.flightNumber.toLong(),
                missionName = launch.missionName,
                launchYear = launch.launchYear,
                rocketId = launch.rocket.id,
                details = launch.details,
                launchSuccess = launch.launchSuccess,
                missionPatchUrl = launch.links.missionPatchUrl,
                articleURL = launch.links.articleUrl
        )
    }

}

