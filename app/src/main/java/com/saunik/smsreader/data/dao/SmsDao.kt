package com.saunik.smsreader.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saunik.smsreader.data.model.Sms

@Dao
interface SmsDao {
    // getAllGroceryItems function is used to get
    // all the data of database.
    @Query("SELECT * FROM sms_table")
    fun getAllSms(): LiveData<List<Sms>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSms(sms: Sms)
}