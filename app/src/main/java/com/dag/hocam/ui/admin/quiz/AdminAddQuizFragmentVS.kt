package com.dag.hocam.ui.admin.quiz

import com.dag.hocam.application.HocamVS

sealed class AdminAddQuizFragmentVS: HocamVS {

    object QuizAdded: AdminAddQuizFragmentVS()
    object Error: AdminAddQuizFragmentVS()
}