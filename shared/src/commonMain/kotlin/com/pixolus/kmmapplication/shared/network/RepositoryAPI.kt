package com.pixolus.kmmapplication.shared.network

import com.pixolus.kmmapplication.shared.entity.RocketLaunch
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

// Note that ktor also has a json object which cannot be used with kotlinx
// therefor the typealias to differ
import kotlinx.serialization.json.Json as kotlinxJson

/**
 * the documentation is using the spaceX API so we will too in a first step.
 * Once we do have our own API we will switch but since the domain objects are also based
 * on spaceX we finish it up and later replace the domain layer and persistence
 * layer. Get it to run on both platforms then adapt.
 * We could just as easily use anything selfmade but that would mean to have to have a server
 * running somewhere. All too complicated for now.
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
           val json = kotlinxJson { ignoreUnknownKeys=true }
            serializer = KotlinxSerializer(json)
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