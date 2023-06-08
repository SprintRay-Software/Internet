package com.sprintray.internet.service

import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemProperties
import android.util.Log
import android.widget.Toast
import com.sprintray.internet.tools.TimeAsyncManager

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Time
import java.text.DecimalFormat
import java.util.*

class LocationClient {
    private var locationManager: LocationManager? = null

    companion object{
        private const val TAG = "LocationClient"
    }



    fun getLocationFromServer() {

        Log.d(TAG, "getLocationFromServer: getCurrentDate : ${TimeAsyncManager.getCurrentDate()}")
        Log.d(TAG, "getLocationFromServer: getCurrentNtpServer : ${TimeAsyncManager.getCurrentNtpServer()}")
        Log.d(TAG, "getLocationFromServer: getCurrentTimeStamp : ${TimeAsyncManager.getCurrentTimeStamp()}")
        Log.d(TAG, "getLocationFromServer: getCurrentTimeMillisDiff : ${TimeAsyncManager.getCurrentTimeMillisDiff()}")
        Log.d(TAG, "getLocationFromServer: getElapsedRealtimeDiff : ${TimeAsyncManager.getElapsedRealtimeDiff()}")

        CoroutineScope(Dispatchers.Main).launch {
            var taskValue =   withContext(Dispatchers.IO) {
                var response: StringBuilder = StringBuilder();
                var urlStr = "https://ipinfo.io/timezone";
                var connection: HttpURLConnection? = null;
                var reader: BufferedReader? = null;
                try {
                    //获取到HttpURLConnection的实例,一般只需new
                    //出一个URL对象，并传入目标的网络地址
                    var url = URL(urlStr);
                    //然后调用一下openConnection()方法
                    connection = url.openConnection() as HttpURLConnection;
                    //设置HTTP请求所使用的方法，
                    connection.setRequestMethod("GET");
                    //进行一些自由的定制操作，比如：设置连接超时、
                    // 读取超时的毫秒数等
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //调用getInputStream()方法获取到服务器返回的输入流
                    var inputStream: InputStream = connection.getInputStream();
                    //下面对获取的输入流进行读取
                    var reader: BufferedReader = BufferedReader(InputStreamReader(inputStream));
                    reader.lineSequence().forEach {
                        response.append(it)

                    }
                    //写入时区
                    SystemProperties.set("persist.sys.timezone",response.toString())
//                    SystemProperties.set("persist.sys.timezone","Asia/Yekaterinburg")

                } catch (e: IOException) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (e: IOException) {
                            e.printStackTrace();
                        }
                    }
                    connection?.disconnect()
                }

                response.toString()
            }


            //根据网络同步当前时间
            TimeAsyncManager.startAsync();

            Log.d(TAG, "getLocationFromServer: ${taskValue} ")

        }



    }







}