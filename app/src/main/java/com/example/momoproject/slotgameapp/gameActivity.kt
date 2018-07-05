package com.example.momoproject.slotgameapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class gameActivity : AppCompatActivity() {
    //入れ子
    private lateinit var leftImage: ImageView
    private lateinit var centerImage: ImageView
    private lateinit var rightImage: ImageView
    private lateinit var stopButton1: Button
    private lateinit var stopButton2: Button
    private lateinit var stopButton3: Button
    private lateinit var resetButton: Button
    private lateinit var resultText: TextView
    private lateinit var coinText: TextView
    //イメージデータ配列
    val imageData = listOf(R.drawable.irasuto1, R.drawable.irasuto2, R.drawable.irasuto3, R.drawable.irasuto4, R.drawable.irasuto5)
    //ランダム用
    private val random = Random()
    //揃ったかどうかの判定用
    var leftNum = 99
    var centerNum = 99
    var rightNum = 99

    //データ管理用
    private lateinit var data: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    //所持コインなどデータ
    private var coin = 0
    private var playCount = 0
    private var winCount = 0

    //デバック用
    private val imageSelectNum = 2;// 1～5まで

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        //TODO　ゲーム画面の作成
        //TODO　掛け金、コインの設定
        init()
        startSet()
        stopButton1.setOnClickListener {
            leftNum = random.nextInt(imageSelectNum)
            leftImage.setImageResource(imageData[leftNum])
            stopButton1.isEnabled = false
            checkSlot()
        }
        stopButton2.setOnClickListener {
            centerNum = random.nextInt(imageSelectNum)
            centerImage.setImageResource(imageData[centerNum])
            stopButton2.isEnabled = false
            checkSlot()
        }
        stopButton3.setOnClickListener {
            rightNum = random.nextInt(imageSelectNum)
            rightImage.setImageResource(imageData[rightNum])
            stopButton3.isEnabled = false
            checkSlot()
        }

        //リセットボタン時の動作
        resetButton.setOnClickListener {
            stopButton1.isEnabled = true
            stopButton2.isEnabled = true
            stopButton3.isEnabled = true

            leftNum = 99
            centerNum = 99
            rightNum = 99

            leftImage.setImageResource(R.mipmap.ic_launcher)
            centerImage.setImageResource(R.mipmap.ic_launcher)
            rightImage.setImageResource(R.mipmap.ic_launcher)
            resultText.text = ""
            resetButton.isEnabled = false
        }

    }

    private fun init() {
        leftImage = findViewById(R.id.imageViewL)
        centerImage = findViewById(R.id.imageViewC)
        rightImage = findViewById(R.id.imageViewR)
        stopButton1 = findViewById(R.id.stopButton1)
        stopButton2 = findViewById(R.id.stopButton2)
        stopButton3 = findViewById(R.id.stopButton3)
        resetButton = findViewById(R.id.resetButton)
        resultText = findViewById(R.id.resultText)
        coinText = findViewById(R.id.coinText)
        data = getSharedPreferences("MainData", Context.MODE_PRIVATE)
        editor = data.edit()
    }

    private fun startSet() {
        coin = data.getInt("coin", 500);
        playCount = data.getInt("play", 0);
        winCount = data.getInt("win", 0);

        resetButton.isEnabled = false
        coinText.text = coin.toString()

    }

    private fun checkSlot() {
        if (leftNum == centerNum && leftNum == rightNum) {
            //数値処理
            coin += 50
            playCount++
            winCount++
            editor.putInt("coin", coin)
            editor.putInt("play", playCount)
            editor.putInt("win", winCount)
            editor.commit()
            //表示
            resultText.text = "おめでとう！！"
            coinText.text = coin.toString()
            resetButton.isEnabled = true
        } else if (!stopButton1.isEnabled && !stopButton2.isEnabled && !stopButton3.isEnabled) {
            //数値処理
            coin -= 10
            playCount++
            editor.putInt("coin", coin)
            editor.putInt("play", playCount)
            editor.commit()
            //表示
            resultText.text = "ざんねん！！"
            coinText.text = coin.toString()
            resetButton.isEnabled = true
        }
    }
}