package com.dag.hocam.ui.quiz.quizfail

import com.dag.hocam.application.HocamVS

sealed class QuizFailVS: HocamVS {
    object QuizCompleted: QuizFailVS()
    object Error: QuizFailVS()
}