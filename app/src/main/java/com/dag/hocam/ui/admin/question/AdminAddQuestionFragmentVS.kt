package com.dag.hocam.ui.admin.question

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.data.topic.TopicResponse

sealed class AdminAddQuestionFragmentVS: HocamVS {

    class SetQuiz(val quizList:List<Quiz>): AdminAddQuestionFragmentVS()
    object QuestionAdded: AdminAddQuestionFragmentVS()
    object Error: AdminAddQuestionFragmentVS()
}