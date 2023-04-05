package com.example.imd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class FaceIdentificationReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        Log.d("FaceIdentificationReceiver","Stop")
        // Intentオブジェクト
        val targetIntent = Intent(context, FaceIdentificationService::class.java)
        // サービスの停止
        context.stopService(targetIntent)
    }
}