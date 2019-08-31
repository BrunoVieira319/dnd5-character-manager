package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.CharacterInfo
import kotlinx.android.synthetic.main.character_info_attribute_item.view.*

class AttributesInfoAdapter(var context: Context, var attibutes: List<CharacterInfo.Attribute>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AttributesInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.character_info_attribute_item, parent, false)
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(attibutes[position])
    }

    override fun getItemCount(): Int {
        return attibutes.size
    }

    class ViewHolder(
        itemView: View,
        var ctx: Context
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bindItems(attribute: CharacterInfo.Attribute) {
            itemView.attributeNameInfo.text = attribute.name
            itemView.attributeValueInfo.text = attribute.value.toString()

            if (attribute.modifier > 0) {
                itemView.attributeModifierInfo.text = "+${attribute.modifier}"
            } else {
                itemView.attributeModifierInfo.text = attribute.modifier.toString()
            }
        }
    }
}