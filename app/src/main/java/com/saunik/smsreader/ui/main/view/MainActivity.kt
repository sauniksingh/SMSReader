package com.saunik.smsreader.ui.main.view

import android.Manifest.permission.RECEIVE_SMS
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.saunik.smsreader.R
import com.saunik.smsreader.data.model.Sms
import com.saunik.smsreader.databinding.ActivityMainBinding
import com.saunik.smsreader.ui.main.adapter.SmsAdapter
import com.saunik.smsreader.ui.main.viewmodel.MainViewModel
import com.saunik.smsreader.utils.Status
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    var REQUEST_SMS = 19
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: SmsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        if (Telephony.Sms.getDefaultSmsPackage(this) != packageName) {
//            val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName)
//            startActivityForResult(intent, 1)
//        } else {
//            val lst: List<Sms> = getAllSms()
//            Log.d("messageOnly", Gson().toJson(lst))
//        }
        if (ContextCompat.checkSelfPermission(
                this,
                RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(RECEIVE_SMS), REQUEST_SMS)
        } else {
            // Permission Granted Display Last received messages only
        }

        setupUI()
        setupObserver()
        mainViewModel.fetchSmsFromDb()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                val myPackageName = packageName
//                if (Telephony.Sms.getDefaultSmsPackage(this) == myPackageName) {
//                    val lst: List<Sms> = getAllSms()
//                    Log.d("messageOnly", Gson().toJson(lst))
//                    for (message in lst) {
//                        Log.d("message", Gson().toJson(message))
//                    }
//                }
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }

//    @SuppressLint("Range")
//    fun getAllSms(): List<Sms> {
//        val lstSms: MutableList<Sms> = ArrayList()
//        var objSms: Sms
//        val message: Uri = Uri.parse("content://sms/")
//        val c: Cursor? = contentResolver.query(message, null, null, null, null)
//        startManagingCursor(c)
//        val totalSMS: Int = c?.count!!
//        if (c.moveToFirst()) {
//            for (i in 0 until totalSMS) {
//                val folder = if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
//                    "inbox"
//                } else {
//                    "sent"
//                }
//                objSms = Sms(
//                    c.getString(c.getColumnIndexOrThrow("_id")),
//                    c.getString(c.getColumnIndexOrThrow("address")),
//                    c.getString(c.getColumnIndexOrThrow("body")),
//                    c.getString(c.getColumnIndex("read")),
//                    c.getString(c.getColumnIndexOrThrow("date")),
//                    folder
//                )
//                lstSms.add(objSms)
//                c.moveToNext()
//            }
//        } else {
//            throw RuntimeException("You have no SMS");
//        }
//        c.close()
//        return lstSms
//    }

    /* And a method to override */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_SMS -> if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                if (ContextCompat.checkSelfPermission(
                        this, RECEIVE_SMS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
//                    val lst: List<Sms> = getAllSms()
//                    Log.d("messageOnly", Gson().toJson(lst))
                }
            } else {
                Toast.makeText(this, "No Permission granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SmsAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.sms.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { users -> renderList(users) }
//                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
//                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
//                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(sms: List<Sms>) {
        adapter.addData(sms)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onMessageRecieve(sms: Sms) {
        Log.d("onMessageRecieve", Gson().toJson(sms))
        val smsList = ArrayList<Sms>()
        smsList.add(sms)
        adapter.addData(smsList)
        mainViewModel.addSms(sms)
    }
}