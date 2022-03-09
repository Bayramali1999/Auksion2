package com.example.auksion2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)


        bindView()
    }

    private fun bindView() {
        back_arrow.setOnClickListener {
            val intent = Intent(this@FilterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}