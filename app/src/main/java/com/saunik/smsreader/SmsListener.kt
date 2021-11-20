package com.saunik.smsreader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import com.saunik.smsreader.data.model.Sms
import com.saunik.smsreader.utils.DateUtil
import org.greenrobot.eventbus.EventBus
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Saunik Singh on 20/11/21.
 */
class SmsListener : BroadcastReceiver() {
    val decimalRegex = "(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)"

    //    val regex = "(([1-9]\\d{0,19}(,\\d{20})*)|(([1-9]\\d*)?\\d))"
    override fun onReceive(p0: Context?, p1: Intent?) {
        // Get SMS map from Intent
        p0?.apply {
            p1?.let {
                if (it.action != (Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                    return
                }
                val receivedMessage = Telephony.Sms.Intents.getMessagesFromIntent(it)
                val messageBuilder = StringBuilder()
                for (message in receivedMessage) {
                    messageBuilder.append(message.messageBody)
                }
                val decimalPattern = Pattern.compile(decimalRegex)
                val decimalMatcher: Matcher = decimalPattern.matcher(messageBuilder)
//                val pattern = Pattern.compile(regex)
//                val m: Matcher = pattern.matcher(messageBuilder)
                if (decimalMatcher.find()) {
                    sendData(receivedMessage[0], decimalMatcher.group(), messageBuilder.toString())
                }
//                else if (m.find()) {
//                    sendData(
//                        receivedMessage[0],
//                        m.group(),
//                        messageBuilder.toString()
//                    )
//                }
            }
        }
    }


    private fun sendData(receivedMessage: SmsMessage, amount: String, message: String) {
        val objSms = Sms(
            receivedMessage.displayOriginatingAddress,
            message,
            DateUtil.getFormatedDate(receivedMessage.timestampMillis),
            amount, receivedMessage.timestampMillis.toInt()
        )
        EventBus.getDefault().post(objSms)
    }
}