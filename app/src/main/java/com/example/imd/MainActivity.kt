package com.example.imd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register : Button = findViewById(R.id.register_button)
        register.setOnClickListener(this)
        val start : Button = findViewById(R.id.start_button)
        start.setOnClickListener(this)
        val stop : Button = findViewById(R.id.stop_button)
        stop.setOnClickListener(this)
    }

    override fun onClick(v: View)
    {
        when(v.id)
        {
            R.id.start_button ->
            {
                Log.i("MainActivity","Start")
                // Intentオブジェクト
                val intent = Intent(this@MainActivity,
                    FaceIdentificationService::class.java)
                // サービスの起動
                startForegroundService(intent)
            }
            R.id.stop_button ->
            {
                Log.i("MainActivity","Stop")
                // Intentオブジェクト
                val intent = Intent(this@MainActivity,
                    FaceIdentificationService::class.java)
                // サービスの停止
                stopService(intent)
            }
        }
    }
}