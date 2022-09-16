package com.bytetrack.sample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.bytetrack.android.sdk.ByteTrack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUserInfo = findViewById<EditText>(R.id.et_user_name)

        findViewById<Button>(R.id.bt_login).setOnClickListener {
            val userInfo = etUserInfo.text.trim().toString()
            if (userInfo.isBlank()) {
                Toast.makeText(this, "请输入用户名", LENGTH_SHORT).show()
            } else {
                ByteTrack.instance.userLogin("111")
                Toast.makeText(this, "设置成功", LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.bt_logout).setOnClickListener {
            ByteTrack.instance.userLogout()
            Toast.makeText(this, "退出账号成功", LENGTH_SHORT).show()
            Toast.makeText(this, "dev退出账号成功", LENGTH_SHORT).show()
        }
    }
}