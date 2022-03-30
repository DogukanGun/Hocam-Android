package com.dag.hocam.ui.admin.topic

import com.dag.hocam.application.HocamVS

sealed class AddTopicVS: HocamVS {
    object TopicAdded: AddTopicVS()
    object Error: AddTopicVS()
}