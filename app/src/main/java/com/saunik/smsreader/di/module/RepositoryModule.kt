package com.saunik.smsreader.di.module

import com.saunik.smsreader.data.repository.SmsRepository
import org.koin.dsl.module

/**
 * Created by Saunik Singh on 20/11/21.
 */

val repoModule = module {
    single {
        SmsRepository(get(), get())
    }
}
