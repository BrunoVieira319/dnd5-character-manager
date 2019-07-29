package com.fundaec.ddcharactermanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import br.com.aioria.insta.GsonRequest
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.Races
import kotlinx.android.synthetic.main.activity_new_character.*

class NewCharacter : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var queue: RequestQueue? = null
    private var races: List<Races.Result>? = null
    private var spinnerRaces: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        queue = Volley.newRequestQueue(baseContext)

        fetchRaces()


    }

    private fun fetchRaces() {
        val url = "http://dnd5eapi.co/api/races"
        val request = GsonRequest(url, Races::class.java,
            Response.Listener { response ->
                races = response.results
                val adapter = ArrayAdapter(
                    baseContext,
                    android.R.layout.simple_spinner_item,
                    races?.map { it -> it?.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

                spinnerRaces = this.spinner_races
                spinnerRaces!!.onItemSelectedListener = this
                spinnerRaces!!.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
    }

}
