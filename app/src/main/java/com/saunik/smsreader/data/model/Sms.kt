package com.saunik.smsreader.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Saunik Singh on 19/11/21.
 */
@Entity(tableName = "sms")
@Parcelize
data class Sms(
    val address: String?,
    val message: String?,
    val time: String?,
    val amount: String?,
    @PrimaryKey
    val id: Int
) : Parcelable
