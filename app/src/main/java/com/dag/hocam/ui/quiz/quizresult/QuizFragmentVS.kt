package com.dag.hocam.ui.quiz.quizresult

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.QuestionResponse

sealed class QuizFragmentVS: HocamVS {
    class SetQuestions(val questionList:List<QuestionResponse>): QuizFragmentVS()
    object Error: QuizResultFragmentVS()
}