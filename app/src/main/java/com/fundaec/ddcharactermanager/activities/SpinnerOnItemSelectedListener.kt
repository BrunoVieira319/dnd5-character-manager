package com.fundaec.ddcharactermanager.activities

import android.view.View
import android.widget.AdapterView
import com.fundaec.ddcharactermanager.models.BaseJson

class SpinnerOnItemSelectedListener(val contentJson: List<BaseJson.Result>) : AdapterView.OnItemSelectedListener  {

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //contentJson.first
    }

}