package com.foxwelltech.multiplatform

import org.koin.core.scope.Scope

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


expect fun Scope.createDatabaseDriverFactory() : DatabaseDriverFactory
