package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.CharacterInfo
import kotlinx.android.synthetic.main.skill_item.view.*

class SkillsInfoAdapter(var context: Context, var skills: List<CharacterInfo.Skill>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<SkillsInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return ViewHolder(v, context)
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(skills[position])
    }

    class ViewHolder(
        itemView: View,
        var ctx: Context
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bindItems(skill: CharacterInfo.Skill) {
            itemView.skillNameInfo.text = skill.name
            if (skill.value > 0) {
                itemView.skillBonus.text = "+${skill.value}"
            } else {
                itemView.skillBonus.text = skill.value.toString()
            }
            if (skill.proficient) {
                itemView.skillNameInfo.setTextColor(Color.GREEN)
            }
        }
    }
}