package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.Skill
import kotlinx.android.synthetic.main.skill_choice_item.view.*

class SkillsAdapter(var context: Context, var skills: List<Skill>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    var selected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.skill_choice_item, parent, false)
        return ViewHolder(v, context, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(skills[position])
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    fun countChoose() {
        selected = 0
        skills.forEach {
            if (it.selected) selected++
        }
    }

    class ViewHolder(
        itemView: View,
        var ctx: Context,
        var skillsAdapter: SkillsAdapter
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems(skill: Skill) {
            itemView.checkBoxSkill.text = skill.skillName
            itemView.checkBoxSkill.setOnCheckedChangeListener { _, _ ->
                skill.selected = !skill.selected
                skillsAdapter.countChoose()
            }
        }
    }
}