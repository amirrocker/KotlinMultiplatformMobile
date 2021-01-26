package com.pixolus.kmmapplication.shared.network

import com.pixolus.kmmapplication.shared.entity.RocketLaunch
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

/**
 * the documentation is using the spaceX API so we will to in a first step.
 * Once we do have our own API we will switch but since the domain is also based
 * on spaceX we finish it up and later replace the domain layer and persistence
 * layer. Get it to run on both platforms then adapt.
 * we may encounter an error since the api has upgraded the api to V4 where it
 * was V3 when the documentation was written.
 *
 * https://docs.spacexdata.com/?version=latest
 * https://github.com/r-spacex/SpaceX-API/tree/master/docs/v4
 *
 */
class RepositoryAPI {

    companion object {
        private const val LAUNCHES_ENDPOINT = "https://api.spacexdata.com/v3/launches"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
           serializer = KotlinxSerializer()
        }
    }

    /**
     * a simple API call to retrieve all launches.
     * Note the suspend keyword indicating a coroutine call or
     * a call to another suspend function as it is the case here.
     * The called get function on the HttpClient is also
     * a suspend function therefor this method must also suspend.
     * This leads to the request being executed in the
     * HttpClient's Threadpool. (Not DeadPool :P)
     */
    suspend fun getAllLaunches():List<RocketLaunch> {
        return httpClient.get(LAUNCHES_ENDPOINT)
    }
}