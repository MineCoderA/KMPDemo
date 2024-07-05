plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

//val skikoNativeX64 by configurations.creating
//val skikoNativeArm64 by configurations.creating
//
//val jniDir = "${projectDir.absolutePath}/src/main/jniLibs"
//
//// TODO: filter .so files only.
//val unzipTaskX64 = tasks.register("unzipNativeX64", Copy::class) {
//    destinationDir = file("$jniDir/x86_64")
//    from(skikoNativeX64.map { zipTree(it) })
//}
//
//val unzipTaskArm64 = tasks.register("unzipNativeArm64", Copy::class) {
//    destinationDir = file("$jniDir/arm64-v8a")
//    from(skikoNativeArm64.map { zipTree(it) })
//}

android {
    namespace = "com.foxwelltech.multiplatform.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.foxwelltech.multiplatform.android"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("arm64-v8a","x86_64")
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)

//    skikoNativeX64(libs.skiko.android.runtime.x64)
//    skikoNativeArm64(libs.skiko.android.runtime.arm64)
}

//tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile>().configureEach {
//    dependsOn(unzipTaskX64)
//    dependsOn(unzipTaskArm64)
//}
//
//tasks.withType<Copy> {
//    // This line needs to properly merge MANIFEST files from jars into dex
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//}