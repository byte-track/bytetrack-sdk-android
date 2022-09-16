package com.bytetrack.sample

import android.app.Application
import com.bytetrack.android.sdk.ByteTrack

/**
 * @Author:      lovelz
 * @CreateDate:  2022/9/1
 * @Description:
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ByteTrack.instance.initMessager(this, "166", "android_sdk323cce97c9e54cbabe58ace4409c4f1b")
    }

}