package com.fundaec.ddcharactermanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.adapters.SkillsInfoAdapter
import com.fundaec.ddcharactermanager.models.CharacterInfo
import com.fundaec.ddcharactermanager.network.GsonRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_main_info.*
import kotlinx.android.synthetic.main.fragment_character_skills.*

private const val CHARACTER_ID = "characterId"

class CharacterSkillsFragment : Fragment() {

    private var characterId: String? = null
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getString(CHARACTER_ID)
        }

        queue = Volley.newRequestQueue(activity?.baseContext)
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val request = GsonRequest(
            "https://character-manager.herokuapp.com/v1/characters/$characterId",
            CharacterInfo::class.java,
            Response.Listener {
                createRecyclerSkills(it.skills)
            },
            Response.ErrorListener {
                Toast.makeText(activity?.baseContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        queue?.add(request)
    }

    private fun createRecyclerSkills(skills: List<CharacterInfo.Skill>) {
        val adapter = SkillsInfoAdapter(
            activity!!.baseContext,
            skills
        )
        recyclerSkillsInfo.adapter = adapter
        recyclerSkillsInfo.layoutManager = GridLayoutManager(activity!!.baseContext, 2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_skills, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: String) =
            CharacterSkillsFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER_ID, characterId)
                }
            }
    }
}
