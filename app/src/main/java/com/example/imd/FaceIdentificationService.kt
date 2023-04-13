package com.example.imd

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder

import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*


class FaceIdentificationService : Service()
{
    class FaceIdentificationTask : TimerTask()
    {
        override fun run()
        {
            //フォアグラウンド実行中の処理
            println("hello")
        }
    }

    private val timer = Timer()
    private val timerTask = FaceIdentificationTask()
    // scheduleAtFixedRateメソッドの引数
    private val delay: Long= 0L
    private val long: Long = 5000L

    companion object
    {
        const val CHANNEL_ID = "1111"
    }

    // Serviceクラスの抽象メソッドの実装(必須)
    // -> サービスをバインドして実行する場合はブロック内{...}に処理を記述
    override fun onBind(p0: Intent?): IBinder?
    {
        return null
    }

    // サービスの実行開始時に行う処理
    // -> サービスの強制終了時の処理を表すServiceクラス定数を返却
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Log.i("FaceIdentificationService","onStartCommand called")

        timer.scheduleAtFixedRate(timerTask, delay, long)

        //1．通知領域タップで戻ってくる先のActivity
        val openIntent = Intent(this, MainActivity::class.java).let {
            PendingIntent.getActivity(this, 0, it,PendingIntent.FLAG_MUTABLE)
        }

        //2．通知チャネル登録
        val channelId = CHANNEL_ID
        val channelName = "IMDService Channel"
        val channel = NotificationChannel(
            channelId, channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        //3．ブロードキャストレシーバーをPendingIntent化
        val sendIntent = Intent(this, FaceIdentificationReceiver::class.java).apply {
            action = Intent.ACTION_SEND
        }
        val sendPendingIntent = PendingIntent.getBroadcast(this, 0, sendIntent, PendingIntent.FLAG_MUTABLE)
        //4．通知の作成（ここでPendingIntentを通知領域に渡す）
        val notification = NotificationCompat.Builder(this, CHANNEL_ID )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("IMD実行中")
            .setContentText("終了する場合はこちらから行って下さい。")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(openIntent)
            .addAction(R.drawable.ic_launcher_foreground, "実行終了", sendPendingIntent)
            .build()

        //5．フォアグラウンド開始。
        startForeground(2222, notification)

        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    // サービスの初期化時に実行する処理
    override fun onCreate()
    {
        Log.i("FaceIdentificationService","Create")
    }

    // サービスの終了時に実行する処理
    override fun onDestroy()
    {
        Log.i("FaceIdentificationService","Destroy")
        timer.cancel()
    }

}