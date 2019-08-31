package com.fundaec.ddcharactermanager.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.adapters.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager, intent.getStringExtra("characterId"))

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }
}