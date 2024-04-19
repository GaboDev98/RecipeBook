package com.gabodev.recipebook

import android.app.Application
import com.gabodev.recipebook.di.mainModule
import com.gabodev.recipebook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipeBookApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RecipeBookApplication)
            modules(mainModule, viewModelModule)
        }
    }
}
