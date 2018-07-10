package com.example.momoproject.slotgameapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var materialButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //宣言
        init()

        startButton.setOnClickListener {
            //画面遷移
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        materialButton.setOnClickListener {
            //画面遷移
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun init() {
        startButton = findViewById(R.id.startButton)
        materialButton = findViewById(R.id.materialButton)
    }
}
