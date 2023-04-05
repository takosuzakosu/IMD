package com.example.imd

import android.app.Service
import android.content.Intent
import android.os.IBinder

import android.util.Log

class FaceIdentificationService : Service() {
    // Serviceクラスの抽象メソッドの実装(必須)
    // -> サービスをバインドして実行する場合はブロック内{...}に処理を記述
    override fun onBind(intent: Intent): IBinder?
    {
        return null
    }

    // サービスの初期化時に実行する処理
    override fun onCreate()
    {
        Log.d("FaceIdentificationService","Create")
    }

    // サービスの実行開始時に行う処理
    // -> サービスの強制終了時の処理を表すServiceクラス定数を返却
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Log.d("FaceIdentificationService","Running")
        return START_NOT_STICKY
    }

    // サービスの終了時に実行する処理
    override fun onDestroy()
    {
        Log.d("FaceIdentificationService","Destroy")
    }
}