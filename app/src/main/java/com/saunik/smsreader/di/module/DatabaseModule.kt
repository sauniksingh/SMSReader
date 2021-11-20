package com.saunik.smsreader.di.module

import android.app.Application
import androidx.room.Room
import com.saunik.smsreader.data.dao.SmsDao
import com.saunik.smsreader.data.db.SmsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Saunik Singh on 20/11/21.
 */

val databaseModule = module {

    fun provideDatabase(application: Application): SmsDatabase {
        return Room.databaseBuilder(application, SmsDatabase::class.java, "sms")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideSmsDao(database: SmsDatabase): SmsDao {
        return database.smsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideSmsDao(get()) }
}