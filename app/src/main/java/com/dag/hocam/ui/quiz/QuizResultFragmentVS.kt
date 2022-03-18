package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.Quiz

sealed class QuizResultFragmentVS: HocamVS {
    object FinishQuiz: QuizResultFragmentVS()
    object Error: QuizResultFragmentVS()
}