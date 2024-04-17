package com.gabodev.recipebook

import android.app.Application
import com.gabodev.recipebook.data.AppContainer
import com.gabodev.recipebook.data.AppContainerImpl
import com.gabodev.recipebook.di.mainModule
import com.gabodev.recipebook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipeBookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
        startKoin {
            androidLogger()
            androidContext(this@RecipeBookApplication)
            modules(mainModule, viewModelModule)
        }
    }
}
