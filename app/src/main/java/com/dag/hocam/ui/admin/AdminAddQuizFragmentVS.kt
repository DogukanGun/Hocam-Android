package com.dag.hocam.ui.admin

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.topic.TopicResponse

sealed class AdminAddQuizFragmentVS: HocamVS {

    class SetTopic(val topicList:List<TopicResponse>): AdminAddQuizFragmentVS()
    object QuizAdded: AdminAddQuizFragmentVS()
    object Error: AdminAddQuizFragmentVS()
}