package br.com.finquest

import android.app.Application
import br.com.finquest.features.home.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                featureModule
            ).androidContext(this@MyApplication)
        }
    }
}