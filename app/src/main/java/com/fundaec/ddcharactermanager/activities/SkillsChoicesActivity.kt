package com.fundaec.ddcharactermanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aioria.insta.GsonRequest
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.adapters.SkillsAdapter
import com.fundaec.ddcharactermanager.models.CharacterClass
import com.fundaec.ddcharactermanager.models.Skill
import kotlinx.android.synthetic.main.activity_skills_choices.*

class SkillsChoicesActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills_choices)
        queue = Volley.newRequestQueue(baseContext)

        fetchClass()

    }

    private fun fetchClass() {
        val request = GsonRequest(intent.getStringExtra("class"), CharacterClass::class.java,
            Response.Listener { characterClass ->
                val choose = characterClass.proficiencyChoices[0].choose
                choices.text = "Choose $choose"

                val skillsChoices = characterClass.proficiencyChoices[0].from
                recyclerSkills.adapter = SkillsAdapter(
                    baseContext,
                    skillsChoices.map { Skill(it.name) },
                    choose
                )
                recyclerSkills.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
            },
            Response.ErrorListener { }
        )
        queue?.add(request)
    }
}
