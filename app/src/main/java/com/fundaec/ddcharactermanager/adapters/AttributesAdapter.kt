package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.Attribute
import kotlinx.android.synthetic.main.attribute_item.view.*

class AttributesAdapter(var context: Context, var attibutes: ArrayList<Attribute>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<AttributesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.attribute_item, parent, false)
        return ViewHolder(v, context, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(attibutes[position])
    }


    override fun getItemCount(): Int {
        return attibutes.size
    }

    private fun downAttribute(adapterPosition: Int) {
        if (adapterPosition != 5) {
            val name = attibutes[adapterPosition].name
            val upName = attibutes[adapterPosition + 1].name
            attibutes[adapterPosition].name = upName
            attibutes[adapterPosition + 1].name = name
            notifyDataSetChanged()
        }
    }

    private fun upAttribute(adapterPosition: Int) {
        if (adapterPosition != 0) {
            val name = attibutes[adapterPosition].name
            val upName = attibutes[adapterPosition - 1].name
            attibutes[adapterPosition].name = upName
            attibutes[adapterPosition - 1].name = name
            notifyDataSetChanged()
        }
    }
    class ViewHolder(
        itemView: View,
        var ctx: Context,
        var attributesAdapter: AttributesAdapter
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bindItems(attribute: Attribute) {
            itemView.attribute.text = attribute.name
            itemView.value.text = attribute.value.toString()
            itemView.buttonUp.setOnClickListener {
                attributesAdapter.upAttribute(adapterPosition)
            }
            itemView.buttonDown.setOnClickListener {
                attributesAdapter.downAttribute(adapterPosition)
            }
        }

    }
}