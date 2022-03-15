package com.dag.hocam.ui.quiz

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.QuestionResponse
import com.dag.hocam.data.quiz.Quiz

sealed class QuizFragmentVS: HocamVS {
    class SetQuestions(val questionList:List<QuestionResponse>): QuizFragmentVS()
}