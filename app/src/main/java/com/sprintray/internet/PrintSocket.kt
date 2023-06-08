package com.sprintray.internet


import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.SPUtils
import com.soonsolid.network.rayware.PrinterConnectInfo
import com.soonsolid.network.rayware.SocketClientListener
import com.soonsolid.network.rayware.UserConnection


import com.sprintray.internet.key.HttpSpKeys
import com.sprintray.internet.tools.BaseContents


import java.io.File

object PrintSocket {

    fun initSocket() {
        SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_NAME).apply {
            if (this.isBlank()) {
                SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME)
                    .put(HttpSpKeys.SPKey.SP_DEVICE_NAME, "PRO_WASH_00001")
            }
        }
        SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_SERIAL).apply {
            if (this.isBlank()) {
                SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME)
                    .put(HttpSpKeys.SPKey.SP_DEVICE_SERIAL, "PRO_WASH_001A")
            }
        }

        SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME)
            .put(HttpSpKeys.SPKey.SP_DEVICE_MODEL, "wash S")
        SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_MODEL).apply {
            if (this.isBlank()) {
                SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME)
                    .put(HttpSpKeys.SPKey.SP_DEVICE_MODEL, "wash S")
            }
        }
        UserConnection.init(49958, PrinterConnectInfo(
            SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_SERIAL),
            SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_NAME),
            SPUtils.getInstance(HttpSpKeys.SPKey.SP_NAME).getString(HttpSpKeys.SPKey.SP_DEVICE_MODEL),
            "0.0.24",
            SPUtils.getInstance().getString("resinData", "") ?: "",
            "0",
            BaseContents.queuePath,
            BaseContents.historyPath,
            mutableListOf()
        ), object : SocketClientListener {
            override fun onCancel() {

            }

            override fun onDisconnect() {
            }

            override fun onPause() {

            }

            override fun onResume() {

            }

            override fun onSprDownloadSuccess(sprPath: String, jobString: String, isQueue: Boolean) {
                Log.d("printSocket", "onSprDownloadSuccess: $sprPath  $jobString  $isQueue ")

            }

            override fun resinDataReceived(resinString: String) {

            }

            override fun qaResinReceived(resinString: String) {

            }




        })
    }


}