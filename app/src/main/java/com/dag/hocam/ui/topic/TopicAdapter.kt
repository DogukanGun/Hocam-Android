package com.dag.hocam.ui.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.databinding.ItemTopicBinding

class TopicAdapter constructor(val topicList:List<TopicResponse>): RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    inner class TopicViewHolder(val binding:ItemTopicBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.binding.topicNameTV.text = topicList[position].topicName
    }

    override fun getItemCount(): Int = topicList.size

}