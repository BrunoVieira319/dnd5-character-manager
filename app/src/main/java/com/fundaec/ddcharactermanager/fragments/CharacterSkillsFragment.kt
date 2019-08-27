package com.fundaec.ddcharactermanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fundaec.ddcharactermanager.R

private const val CHARACTER_ID = "characterId"

class CharacterSkillsFragment : Fragment() {

    private var characterId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getString(CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
