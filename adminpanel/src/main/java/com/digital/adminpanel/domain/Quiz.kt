package com.digital.adminpanel.domain

import com.google.firebase.firestore.PropertyName

data class Quiz(
    @get:PropertyName("questions") @set:PropertyName("questions")
    var questions: Map<String, Question> = emptyMap()
) {
    data class Question(
        @get:PropertyName("name") @set:PropertyName("name")
        var name: String = "",

        @get:PropertyName("answers") @set:PropertyName("answers")
        var answers: Map<String, Answer> = emptyMap()
    ) {
        data class Answer(
            @get:PropertyName("answer_name") @set:PropertyName("answer_name")
            var answerName: String = "",

            @get:PropertyName("point_number") @set:PropertyName("point_number")
            var pointNumber: Int = 0
        )
    }
}