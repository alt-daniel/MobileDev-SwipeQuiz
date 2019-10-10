package com.example.swipequiz

/**
 * Represents a question.
 * @property name - The question it self.
 * @property answer - The answer on the question.
 */
data class Question(
    var name: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS =  arrayOf(
            "A val and var are the same",
            "Mobile Application Development grants 12 EC",
            "A unit in Kotlin corresponds to a void in Java",
            "In Kotlin 'when' replaces the 'switch' operator in Java"
        )
        val ANSWER = arrayOf(
            false,
            true,
            true,
            true
        )
    }
}