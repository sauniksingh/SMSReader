package com.saunik.smsreader

import android.app.Application
import com.saunik.smsreader.di.module.appModule
import com.saunik.smsreader.di.module.databaseModule
import com.saunik.smsreader.di.module.repoModule
import com.saunik.smsreader.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Saunik Singh on 20/11/21.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, repoModule, viewModelModule, databaseModule)
        }
    }
}