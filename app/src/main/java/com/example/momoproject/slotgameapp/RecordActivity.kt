package com.example.momoproject.slotgameapp

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class RecordActivity : AppCompatActivity() {
    //入れ子
    private lateinit var playCountText: TextView
    private lateinit var winCountText: TextView
    private lateinit var winPerText: TextView
    private lateinit var haveCoinText: TextView
    private lateinit var resetButton: Button

    //データ管理用
    private lateinit var data: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record)

        //TODO 履歴・勝率等の表示を行う
        //初期宣言
        init()

        //表示の変更
        viewInformation()

        resetButton.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("削除")
                setMessage("今までの記録を削除してもいいですか？")
                setPositiveButton("はい", DialogInterface.OnClickListener { dialog, which ->
                    //初期化
                    editor.putInt("play", 0)
                    editor.putInt("win", 0)
                    editor.putInt("coin", 500)
                    editor.commit()
                    //表示の変更
                    viewInformation()
                })
                setNegativeButton("いいえ", null)
                show()
            }
        }
    }

    private fun init() {
        playCountText = findViewById(R.id.playCountTextView)
        winCountText = findViewById(R.id.winCountTextView)
        winPerText = findViewById(R.id.winPerTextView)
        haveCoinText = findViewById(R.id.coinTextView)

        resetButton = findViewById(R.id.recordResetButton)
        data = getSharedPreferences("MainData", Context.MODE_PRIVATE)
        editor = data.edit()
    }

    private fun viewInformation() {
        //表示の変更
        playCountText.text = getString(R.string.count, data.getInt("play", 0))
        winCountText.text = getString(R.string.count, data.getInt("win", 0))
        if (data.getInt("play", 1) != 0) {
            winPerText.text = getString(R.string.per, data.getInt("win", 0).toDouble() / data.getInt("play", 1).toDouble() * 100.0)
        } else {
            winPerText.text = getString(R.string.per, data.getInt("win", 0).toDouble() / 1.0 * 100.0)
        }
        haveCoinText.text = getString(R.string.have_coin, data.getInt("coin", 500))
    }
}