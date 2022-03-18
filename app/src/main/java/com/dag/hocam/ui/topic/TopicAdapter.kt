package com.dag.hocam.ui.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.ItemSoonBinding
import com.dag.hocam.databinding.ItemTopicBinding

class TopicAdapter constructor(val topicList:List<TopicResponse>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener:TopicListener? = null
    var soonActive = false
    inner class TopicViewHolder(val binding:ItemTopicBinding): RecyclerView.ViewHolder(binding.root)
    inner class SoonViewHolder(val binding:ItemSoonBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (soonActive){
            val binding = ItemSoonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return SoonViewHolder(binding)
        }else{
            val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return TopicViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!soonActive){
            (holder as TopicViewHolder).binding.apply {
                val topic = topicList[position]
                topicNameTV.text = topic.topicName
                root.setOnClickListener{
                    listener?.topicClicked(topic)
                }
            }
        }
    }

    override fun getItemCount(): Int = if (!soonActive) topicList.size else 1

}