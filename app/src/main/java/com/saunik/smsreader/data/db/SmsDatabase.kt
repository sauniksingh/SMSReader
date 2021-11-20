package com.saunik.smsreader.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saunik.smsreader.data.converter.Converters
import com.saunik.smsreader.data.converter.SmsConverter
import com.saunik.smsreader.data.dao.SmsDao
import com.saunik.smsreader.data.model.Sms

/**
 * Created by Saunik Singh on 20/11/21.
 */
//@Database(entities = [Sms::class], version = 1, exportSchema = false)
//abstract class SmsDatabase : RoomDatabase() {
//
//    abstract fun getSmsDao(): SmsDao
//
//    companion object {
//        @Volatile
//        private var instance: SmsDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                SmsDatabase::class.java,
//                "sms.db"
//            ).build()
//    }
//}

@Database(
    entities = [Sms::class],
    version = 1, exportSchema = false
)

@TypeConverters(
    Converters::class,
    SmsConverter::class
)
abstract class SmsDatabase : RoomDatabase() {
    abstract val smsDao: SmsDao
}