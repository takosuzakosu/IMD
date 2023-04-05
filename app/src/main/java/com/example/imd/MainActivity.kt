package com.example.imd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View)
    {
        when(v.id)
        {
            R.id.start_button ->
            {
                // Intentオブジェクト
                val intent = Intent(this@MainActivity,
                    FaceIdentificationService::class.java)
                // サービスの起動
                startService(intent)
            }
            R.id.stop_button ->
            {
                // Intentオブジェクト
                val intent = Intent(this@MainActivity,
                    FaceIdentificationService::class.java)
                // サービスの起動
                stopService(intent)
            }
        }
    }
}