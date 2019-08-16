package com.fundaec.ddcharactermanager.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aioria.insta.GsonRequest
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.adapters.CustomAdapter
import com.fundaec.ddcharactermanager.models.Attribute
import com.fundaec.ddcharactermanager.models.AttributesEnum
import com.fundaec.ddcharactermanager.models.Race
import kotlinx.android.synthetic.main.activity_point_buy.*

class PointBuyActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var abilityBonuses: HashMap<String, Int>? = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fundaec.ddcharactermanager.R.layout.activity_point_buy)
        queue = Volley.newRequestQueue(baseContext)

        var attributes = arrayListOf(
            Attribute(AttributesEnum.STRENGTH.attributeName, 15),
            Attribute(AttributesEnum.DEXTERITY.attributeName, 14),
            Attribute(AttributesEnum.CONSTITUTION.attributeName, 13),
            Attribute(AttributesEnum.WISDOM.attributeName, 12),
            Attribute(AttributesEnum.WISDOM.attributeName, 10),
            Attribute(AttributesEnum.CHARISMA.attributeName, 8)
        )
        recycler_attributes.adapter = CustomAdapter(baseContext, attributes)
        recycler_attributes.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)

        fetchRacialBonus()
        val bonusAsString = abilityBonuses?.filter { it.value != 0 }?.map { "+${it.value} ${it.key}" }
    }

    private fun fetchRacialBonus() {
        val request = GsonRequest(intent.getStringExtra("race"), Race::class.java,
            Response.Listener { race ->
                race.abilityBonuses.forEachIndexed { index, bonus ->
                    abilityBonuses?.put(AttributesEnum.values()[index].attributeName, bonus)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

}
