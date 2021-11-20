package com.saunik.smsreader.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Saunik Singh on 19/11/21.
 */
@Entity(tableName = "sms_table")
data class Sms(
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "message")
    val message: String?,
    @ColumnInfo(name = "time")
    val time: String?,
    @ColumnInfo(name = "amount")
    val amount: String?,
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int
)
