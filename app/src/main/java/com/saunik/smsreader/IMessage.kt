package com.saunik.smsreader

import android.telephony.SmsMessage

/**
 * Created by Saunik Singh on 20/11/21.
 */
interface IMessage {
    fun receivedMessage(smsMessages: Array<SmsMessage>)
}