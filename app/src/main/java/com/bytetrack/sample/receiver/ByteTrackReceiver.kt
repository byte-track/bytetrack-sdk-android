package com.bytetrack.sample.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bytetrack.android.sdk.data.model.ServiceActionModel
import com.bytetrack.android.sdk.receiver.NotificationClickReceiver

/**
 * @Author:      lovelz
 * @CreateDate:  2023/1/14
 * @Description: ByteTrack广播服务接收器，通知相关回调
 */
class ByteTrackReceiver : NotificationClickReceiver() {
    override fun onNotificationClick(context: Context?, serviceActionModel: ServiceActionModel?) {

    }

    override fun onNotificationNumberChange(context: Context?, redCount: Int) {

    }

}