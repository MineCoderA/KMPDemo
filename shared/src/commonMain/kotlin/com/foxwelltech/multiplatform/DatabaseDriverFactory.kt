package com.foxwelltech.multiplatform

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}