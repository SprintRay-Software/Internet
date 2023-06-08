package com.sprintray.internet.tools

import android.net.SntpClient
import android.os.SystemClock
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object TimeAsyncManager {

    private var TAG = this.javaClass.simpleName

    @Volatile
    private var elapsedRealtimeDiff: Long? = null

    @Volatile
    private var currentTimeMillisDiff: Long? = null

    @Volatile
    private var currentNtpServer: String? = null

    private var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")

    /** ntp服务器列表 */
    private var ntpServerList = arrayOf(
        "ntp.aliyun.com",
        "ntp1.aliyun.com",
        "ntp2.aliyun.com",
        "ntp3.aliyun.com",
        "ntp4.aliyun.com",
        "ntp5.aliyun.com",
        "ntp6.aliyun.com",
        "ntp7.aliyun.com"
    )

    private fun asyncTime(ntpUrl: String): Boolean {
        try {
            currentNtpServer = ntpUrl
            var sntpClient = SntpClient()
            if (sntpClient.requestTime(ntpUrl, 3000)) {
                var serverTimeStamp = sntpClient.ntpTime
                if (serverTimeStamp == 0L) {
                    return false
                }
                elapsedRealtimeDiff = serverTimeStamp - SystemClock.elapsedRealtime()
                currentTimeMillisDiff = serverTimeStamp - System.currentTimeMillis()
                Log.e(TAG, "同步服务器时间成功：server:$ntpUrl, elapsedRealtimeDiff：$elapsedRealtimeDiff ,currentTimeMillisDiff->$currentTimeMillisDiff")
                //用来监控用户同步服务器情况(观察)，涉及用户隐私，暂时去掉
//                var json = JSONObject()
//                json.put("ntp_server",ntpUrl)
//                json.put("ntp_timestamp",serverTimeStamp.toString())
//                json.put("elapsed_realtime_diff",elapsedRealtimeDiff.toString())
//                json.put("current_time_millis_diff",currentTimeMillisDiff.toString())
//                ApmStatUtils.trackAction("ntp_async_event", json)
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "同步服务器时间失败：server:$ntpUrl,error:${e.message}")
        }
        return false
    }

    /**
     * 当前连接的ntp服务器
     */
    @JvmStatic
    fun getCurrentNtpServer(): String? {
        return currentNtpServer
    }

    /**
     * 开机时间与服务器时间误差
     */
    @JvmStatic
    fun getElapsedRealtimeDiff(): Long? {
        return elapsedRealtimeDiff
    }

    /**
     * 本地时间与服务器时间误差
     */
    @JvmStatic
    fun getCurrentTimeMillisDiff(): Long? {
        return currentTimeMillisDiff
    }

    @JvmStatic
    fun startAsync() {
        Thread(Runnable out@{
            ntpServerList.forEach {
                if (asyncTime(it)) {
                    return@out
                }
            }
        }).start()
    }

    /**
     * 获取当前时间戳
     */
    @JvmStatic
    fun getCurrentTimeStamp(): Long {
        var df = elapsedRealtimeDiff ?: return System.currentTimeMillis()
        return SystemClock.elapsedRealtime() + df
    }

    /**
     * 获取日期字符串
     */
    @JvmStatic
    fun getCurrentDate(): String {
        var timeStamp = getCurrentTimeStamp()
        return sdf.format(Date(timeStamp))
    }

}