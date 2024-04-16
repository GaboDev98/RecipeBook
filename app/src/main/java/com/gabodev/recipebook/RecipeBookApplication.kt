package com.gabodev.recipebook

import android.app.Application
import com.gabodev.recipebook.data.AppContainer
import com.gabodev.recipebook.data.AppContainerImpl

class RecipeBookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}
