package com.dag.hocam.ui.topic

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.topic.TopicResponse

sealed class TopicFragmentVS: HocamVS {
    class SetTopic(val topicList:List<TopicResponse>): TopicFragmentVS()
}