package com.saunik.smsreader.di.module

import android.content.Context
import com.saunik.smsreader.cache.SharedPreferenceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Saunik Singh on 20/11/21.
 */
private fun provideCacheModule(context: Context) = SharedPreferenceImpl(context)
val cache = module {
    single { provideCacheModule(androidContext()) }
}