package com.fundaec.ddcharactermanager.activities

import android.content.Intent
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
import com.fundaec.ddcharactermanager.models.BaseJson
import kotlinx.android.synthetic.main.activity_new_character.*

class NewCharacterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var queue: RequestQueue? = null
    private var races: List<BaseJson.Result>? = null
    private var spinnerRaces: Spinner? = null
    private var classes: List<BaseJson.Result>? = null
    private var spinnerClasses: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fundaec.ddcharactermanager.R.layout.activity_new_character)
        queue = Volley.newRequestQueue(baseContext)

        fetchRaces()
        fetchClasses()

        next.setOnClickListener { view ->
            var intent = Intent(baseContext, PointBuyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchClasses() {
        val url = "http://dnd5eapi.co/api/classes"
        val request = GsonRequest(url, BaseJson::class.java,
            Response.Listener { response ->
                classes = response.results
                val adapter = ArrayAdapter(
                    baseContext,
                    android.R.layout.simple_spinner_item,
                    classes?.map { it -> it.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

                spinnerClasses = this.spinner_classes
                spinnerClasses!!.onItemSelectedListener = SpinnerOnItemSelectedListener(classes!!)
                spinnerClasses!!.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

    private fun fetchRaces() {
        val url = "http://dnd5eapi.co/api/contentJson"
        val request = GsonRequest(url, BaseJson::class.java,
            Response.Listener { response ->
                races = response.results
                val adapter = ArrayAdapter(
                    baseContext,
                    android.R.layout.simple_spinner_item,
                    races?.map { it -> it.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

                spinnerRaces = this.spinner_races
                spinnerRaces!!.onItemSelectedListener = SpinnerOnItemSelectedListener(races!!)
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
