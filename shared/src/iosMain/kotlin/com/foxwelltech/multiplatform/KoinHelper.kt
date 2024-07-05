package com.foxwelltech.multiplatform

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {

    private val sdk: SpaceXSDK by inject<SpaceXSDK>()

    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        return sdk.getLaunches(forceReload = forceReload)
    }

    fun initKoin() {
        startKoin {
            modules(appModule)
        }
    }
}