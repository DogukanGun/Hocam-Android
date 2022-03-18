package com.dag.hocam.ui.askquestion

import com.dag.hocam.application.HocamVS

sealed class AskFragmentVS: HocamVS {
    object QuestionSent: AskFragmentVS()
    object Error: AskFragmentVS()
}