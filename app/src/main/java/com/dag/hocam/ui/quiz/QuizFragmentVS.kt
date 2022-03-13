package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.Quiz

sealed class QuizFragmentVS: HocamVS {
    class SetAdapter(val quizList: List<Quiz>): QuizFragmentVS()
}