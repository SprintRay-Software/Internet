package com.sprintray.internet.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class TelemetryService : Service(){

    private var receiver: SystemTimeChangedReceiver? = null;

    companion object{
        private const val TAG = "TelemetryService"
    }
    
    
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()

        initReceiver()

        LocationClient().getLocationFromServer();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        return super.onStartCommand(intent, flags, startId)
    }




    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        Log.d(TAG, "onDestroy: ")
    }



    private fun initReceiver() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_TIME_TICK)
        receiver = SystemTimeChangedReceiver()
        registerReceiver(receiver, filter)
    }


    private class SystemTimeChangedReceiver : BroadcastReceiver() {



        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_TIME_TICK) {

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                 var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")

                Log.d(
                    "Telemetry",
                    "系统时间 ${System.currentTimeMillis()} ${calendar.get(Calendar.YEAR)}年 ${calendar.get(Calendar.MONTH)}月 ${calendar.get(Calendar.DAY_OF_MONTH)}日" +
                            " ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}:${calendar.get(Calendar.SECOND)
                    }"
                )






            }
        }


    }



}