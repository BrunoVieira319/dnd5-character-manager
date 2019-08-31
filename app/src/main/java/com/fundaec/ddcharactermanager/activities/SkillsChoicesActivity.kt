package com.fundaec.ddcharactermanager.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.MainActivity
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.adapters.SkillsAdapter
import com.fundaec.ddcharactermanager.models.CharacterClass
import com.fundaec.ddcharactermanager.models.NewCharacterPostDto
import com.fundaec.ddcharactermanager.models.Skill
import com.fundaec.ddcharactermanager.network.GsonRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_skills_choices.*

class SkillsChoicesActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var choose: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills_choices)
        queue = Volley.newRequestQueue(baseContext)

        fetchClass()
        buttonSaveCharacter.setOnClickListener {
            val adapter = recyclerSkills.adapter as SkillsAdapter
            if (adapter.selected != this.choose) {
                Toast.makeText(baseContext, "You should remove or choose skills", Toast.LENGTH_SHORT).show()
            } else {
                buttonSaveCharacter.isEnabled = false
                saveCharacter()
            }
        }
    }

    private fun saveCharacter() {
        val skillsAdapter = recyclerSkills.adapter as SkillsAdapter
        val character = NewCharacterPostDto(
            intent.getStringExtra("characterName"),
            intent.getStringExtra("class"),
            intent.getStringExtra("race"),
            intent.getParcelableArrayListExtra("attributes"),
            skillsAdapter.skills
        )
        val request = GsonRequest(
            Request.Method.POST,
            "https://character-manager.herokuapp.com/v1/characters",
            String::class.java,
            Gson().toJson(character),
            Response.Listener {
                startActivity(Intent(baseContext, MainActivity::class.java))
                finishAffinity()
            },
            Response.ErrorListener {
                Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                buttonSaveCharacter.isEnabled = true
            }
        )
        queue?.add(request)
    }

    private fun fetchClass() {
        val request = GsonRequest(intent.getStringExtra("class"), CharacterClass::class.java,
            Response.Listener { characterClass ->
                this.choose = characterClass.proficiencyChoices[0].choose
                choices.text = "Choose $choose"

                createRecyclerSkills(characterClass)
            },
            Response.ErrorListener {
                Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

    private fun createRecyclerSkills(characterClass: CharacterClass) {
        val skillsChoices = characterClass.proficiencyChoices[0].from
        recyclerSkills.adapter = SkillsAdapter(
            baseContext,
            skillsChoices.map { Skill(it.name) }
        )
        recyclerSkills.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
    }
}
