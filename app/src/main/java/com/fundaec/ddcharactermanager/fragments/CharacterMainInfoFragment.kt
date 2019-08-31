package com.fundaec.ddcharactermanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.adapters.AttributesInfoAdapter
import com.fundaec.ddcharactermanager.models.CharacterInfo
import com.fundaec.ddcharactermanager.network.GsonRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_main_info.*

private const val CHARACTER_ID = "characterId"

class CharacterMainInfoFragment : Fragment() {

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
                raceClassInfo.text = "${it.race} ${it.characterClass} Lv ${it.level}"
                characterNameInfo.text = it.characterName
                Picasso.get()
                    .load(it.image)
                    .fit()
                    .centerCrop()
                    .into(characterImageInfo)
                createRecyclerAttributes(it.attributes)
            },
            Response.ErrorListener {
                Toast.makeText(activity?.baseContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        queue?.add(request)
    }

    private fun createRecyclerAttributes(attributes: List<CharacterInfo.Attribute>) {
        val adapter = AttributesInfoAdapter(
            activity!!.baseContext,
            attributes
        )
        recyclerAttributesInfo.adapter = adapter
        recyclerAttributesInfo.layoutManager = GridLayoutManager(activity!!.baseContext, 2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_main_info, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: String) =
            CharacterMainInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER_ID, characterId)
                }
            }
    }
}
