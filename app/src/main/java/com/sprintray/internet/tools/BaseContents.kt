package com.sprintray.internet.tools

import android.os.Environment
import com.soonsolid.sprFile.SprFile

object BaseContents {
    const val sprDecodeKey = "WheatiesAreTheBreakfastOfChampions"
    const val magicString = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
    val queuePath = Environment.getExternalStorageDirectory().absolutePath.plus("/queue")
    val queueJsonPath = queuePath.plus("/list.json")
    val historyPath = Environment.getExternalStorageDirectory().absolutePath.plus("/history")
    val historyJsonPath = historyPath.plus("/list.json")
    val queueTempPath = queuePath.plus("/temp/")
    const val MaxTempSize = 2L * 1024 * 1024 * 1024
    var sprFile: SprFile? = null
    val printSprPath = Environment.getExternalStorageDirectory().absolutePath.plus("/current.spr")
    val tankInfoLogPath = Environment.getExternalStorageDirectory().absolutePath.plus("/tankInfo")
    //val model = if (BuildConfig.FLAVOR == "se") Model.SE else Model.TAURUS


}