package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.CharacterMainDto
import kotlinx.android.synthetic.main.character_layout_item.view.*

class CharactersAdapter(var context: Context, var characters: List<CharacterMainDto>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.character_layout_item, parent, false)
        return ViewHolder(v, context, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class ViewHolder(
        itemView: View,
        var ctx: Context,
        var charactersAdapter: CharactersAdapter
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems(characterMainDto: CharacterMainDto) {
            itemView.characterClass.text = characterMainDto.nameCharacter
            itemView.characterClass.text = characterMainDto.characterClass
            itemView.race.text = characterMainDto.race
            itemView.characterName.text = characterMainDto.level.toString()
        }
    }
}