package com.dag.hocam.ui.topic

import com.dag.hocam.data.topic.TopicResponse

interface TopicListener {
    fun topicClicked(topic:TopicResponse)
}