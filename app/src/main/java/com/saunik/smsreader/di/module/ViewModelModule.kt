package com.saunik.smsreader.di.module

import com.saunik.smsreader.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Saunik Singh on 20/11/21.
 */

val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}