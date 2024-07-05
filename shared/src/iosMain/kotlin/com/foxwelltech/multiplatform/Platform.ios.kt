package com.foxwelltech.multiplatform

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.scope.Scope
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun Scope.createDatabaseDriverFactory(): DatabaseDriverFactory {
    return IOSDatabaseDriverFactory()
}

fun MainViewController() = ComposeUIViewController { App() }