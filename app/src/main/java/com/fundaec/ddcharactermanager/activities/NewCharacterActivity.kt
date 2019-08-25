package com.fundaec.ddcharactermanager.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.aioria.insta.GsonRequest
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.BaseJson
import kotlinx.android.synthetic.main.activity_new_character.*

class NewCharacterActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var races: List<BaseJson.Result>? = null
    private var classes: List<BaseJson.Result>? = null
    private var spinnerRaces: Spinner? = null
    private var spinnerClasses: Spinner? = null
    private var selectedRace: String = ""
    private var selectedClass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        queue = Volley.newRequestQueue(baseContext)

        fetchRaces()
        fetchClasses()

        buttonNextCharacterChoices.setOnClickListener {
            if (characterNameInput.text.isBlank()) {
                Toast.makeText(baseContext, "Insert a name for your character", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(baseContext, PointBuyActivity::class.java)
                intent.putExtra("characterName", characterNameInput.text.toString())
                intent.putExtra("class", selectedClass)
                intent.putExtra("race", selectedRace)
                startActivity(intent)
            }
        }
    }

    private fun fetchClasses() {
        val url = "http://dnd5eapi.co/api/classes"
        val request = GsonRequest(url, BaseJson::class.java,
            Response.Listener { response ->
                classes = response.results
                val adapter = ArrayAdapter(
                    baseContext,
                    R.layout.spinner_text_item,
                    classes?.map { it -> it.name }
                )
                adapter.setDropDownViewResource(R.layout.spinner_text_item)

                spinnerClasses = this.spinner_classes
                spinnerClasses!!.adapter = adapter
                spinnerClasses!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedClass = classes!![position].url
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

    private fun fetchRaces() {
        val url = "http://dnd5eapi.co/api/races"
        val request = GsonRequest(url, BaseJson::class.java,
            Response.Listener { response ->
                races = response.results
                val adapter = ArrayAdapter(
                    baseContext,
                    R.layout.spinner_text_item,
                    races?.map { it -> it.name }
                )
                adapter.setDropDownViewResource(R.layout.spinner_text_item)

                spinnerRaces = this.spinner_races
                spinnerRaces!!.adapter = adapter
                spinnerRaces!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedRace = races!![position].url
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }
}