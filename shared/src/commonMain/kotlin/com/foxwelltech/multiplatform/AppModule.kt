package com.foxwelltech.multiplatform

import com.foxwelltech.multiplatform.network.SpaceXApi
import org.koin.compose.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = createDatabaseDriverFactory(),
            api = get()
        )
    }
    viewModel { RocketLaunchViewModel(sdk = get()) }
}