package com.dag.hocam.ui.admin

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.topic.TopicResponse
import com.dag.hocam.ui.admin.question.AdminAddQuestionFragmentVS

sealed class AdminFragmentVS: HocamVS {

    class SetTopic(val topicList:List<TopicResponse>): AdminFragmentVS()

}