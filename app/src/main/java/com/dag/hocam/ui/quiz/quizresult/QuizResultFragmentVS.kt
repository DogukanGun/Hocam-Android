package com.dag.hocam.ui.quiz.quizresult

import com.dag.hocam.application.HocamVS

sealed class QuizResultFragmentVS: HocamVS {
    object FinishQuiz: QuizResultFragmentVS()
    object Error: QuizResultFragmentVS()
}