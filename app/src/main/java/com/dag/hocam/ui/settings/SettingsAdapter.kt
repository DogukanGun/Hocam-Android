package com.dag.hocam.ui.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.data.settings.SettingsMenu
import com.dag.hocam.databinding.ItemTopicBinding

class SettingsAdapter constructor(val context:Context,val settingsList: List<SettingsMenu>): RecyclerView.Adapter<SettingsAdapter.SettingsHolder>() {

    var listener:SettingsAdapterListener? = null

    inner class SettingsHolder(val binding:ItemTopicBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SettingsHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsHolder, position: Int) {
        holder.binding.topicNameTV.text = context.getText(settingsList[position].text)
        holder.binding.root.setOnClickListener { listener?.itemSelected(settingsList[position]) }
    }

    override fun getItemCount(): Int = settingsList.size


    interface SettingsAdapterListener{
        fun itemSelected(settingsMenu:SettingsMenu)
    }
}