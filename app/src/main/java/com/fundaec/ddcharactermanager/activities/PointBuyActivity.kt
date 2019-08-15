package com.fundaec.ddcharactermanager.activities

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import kotlinx.android.synthetic.main.activity_point_buy.*

class PointBuyActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var spinners: HashMap<String, Spinner>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fundaec.ddcharactermanager.R.layout.activity_point_buy)

        spinners = hashMapOf(
            "spinnerStrength" to spinnerStrength,
            "spinnerDexterity" to spinnerDexterity,
            "spinnerConstitution" to spinnerConstitution,
            "spinnerIntelligence" to spinnerIntelligence,
            "spinnerWisdom" to spinnerWisdom,
            "spinnerCharisma" to spinnerCharisma)

        val adapter = ArrayAdapter(
            baseContext,
            R.layout.simple_spinner_item,
            arrayListOf(15, 14, 13, 12, 10, 8)
        )

        spinners!!.forEach {
            it.value.onItemSelectedListener = this
            it.value.adapter = adapter
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }
}
