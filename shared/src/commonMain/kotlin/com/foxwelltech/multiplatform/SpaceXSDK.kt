package com.foxwelltech.multiplatform

import com.foxwelltech.multiplatform.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory, val api: SpaceXApi) {

    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }
        }
    }
}