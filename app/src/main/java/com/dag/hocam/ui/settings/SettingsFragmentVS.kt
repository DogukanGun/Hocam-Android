package com.dag.hocam.ui.settings

import com.dag.hocam.application.HocamVS
import com.dag.hocam.data.questionfromuser.AddQuestionFromUserResponse
import com.dag.hocam.data.questionfromuser.SolveQuestionResponse
import com.dag.hocam.data.quiz.SolvedQuizResponse

sealed class SettingsFragmentVS: HocamVS {
    class SetQuizzes(val quizzes:List<AddQuestionFromUserResponse>): SettingsFragmentVS()
    object Error: SettingsFragmentVS()
    class SetSolvedQuizzes(val solvedQuizzes: List<SolvedQuizResponse>): SettingsFragmentVS()
    class SetNotAnsweredQuestion(val notAnsweredQuestion:AddQuestionFromUserResponse): SettingsFragmentVS()
}