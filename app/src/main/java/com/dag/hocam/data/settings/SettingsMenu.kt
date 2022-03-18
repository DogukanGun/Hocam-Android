package com.dag.hocam.data.settings

import com.dag.hocam.R
import com.dag.hocam.data.user.UserOperationType

enum class SettingsMenu(
    val text:Int,
    val authentication:UserType
) {

    SolvedQuestions(R.string.solved_questions,UserType.USER),
    AnswerQuestions(R.string.answer_questions,UserType.ADMIN)
}