package com.dag.hocam.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.databinding.ItemTopicBinding

class SettingsQuizAdapter constructor(val adapterList:List<String>): RecyclerView.Adapter<SettingsQuizAdapter.SettingsQuizHolder>() {

    var settingsListener:SettingsQuizAdapterListener? = null

    inner class SettingsQuizHolder(val binding:ItemTopicBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsQuizHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SettingsQuizHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsQuizHolder, position: Int) {
        holder.binding.topicNameTV.text = adapterList[position]
        holder.binding.root.setOnClickListener{ settingsListener?.cellClicked(adapterList[position].toInt()) }
        if (settingsListener == null){
            holder.binding.imageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = adapterList.size

    interface SettingsQuizAdapterListener{
        fun cellClicked(id:Int)
    }
}