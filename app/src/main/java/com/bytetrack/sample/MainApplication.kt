package com.bytetrack.sample

import android.app.Application
import com.bytestrack.android.sdk.BytesTrack

/**
 * @Author:      lovelz
 * @CreateDate:  2022/9/1
 * @Description:
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        BytesTrack.instance.initMessager(this, "167", "android_sdk09bb2169cf61448c9ef633f7c1a68bb7")
    }

}