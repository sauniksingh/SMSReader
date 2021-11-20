package com.saunik.smsreader.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saunik.smsreader.data.model.Sms
import com.saunik.smsreader.data.repository.SmsRepository
import com.saunik.smsreader.utils.NetworkHelper
import com.saunik.smsreader.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Saunik Singh on 20/11/21.
 */
class MainViewModel(
    private val mainRepository: SmsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _sms = MutableLiveData<Resource<List<Sms>>>()
    val sms: LiveData<Resource<List<Sms>>>
        get() = _sms

    // Fetch data from API
    fun fetchSmsApi() {
        viewModelScope.launch {
            _sms.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getApiSms().let {
                    if (it.isSuccessful) {
                        _sms.postValue(Resource.success(it.body()))
                    } else _sms.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _sms.postValue(Resource.error("No internet connection", null))
        }
    }

    // Fetch data from Db
    fun fetchSmsFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getDbSms().let {
                _sms.postValue(Resource.success(it.value))
            }
        }
    }

    fun addSms(sms: Sms) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.postSms(sms)
        }
    }
}