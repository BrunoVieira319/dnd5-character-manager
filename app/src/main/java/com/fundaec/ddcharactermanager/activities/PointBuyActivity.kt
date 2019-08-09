package com.fundaec.ddcharactermanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PointBuyActivity : AppCompatActivity() {

    private val attributes = arrayListOf("Strength", "Dexterity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fundaec.ddcharactermanager.R.layout.activity_point_buy)
    }
}