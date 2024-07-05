package com.foxwelltech.multiplatform

import androidx.compose.runtime.Composable
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
fun MainView() = App()


actual fun Scope.createDatabaseDriverFactory(): DatabaseDriverFactory {
    return AndroidDatabaseDriverFactory(androidContext())
}