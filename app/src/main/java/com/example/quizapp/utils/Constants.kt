package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {
    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1, "What country does this flag belong?",
            R.drawable.italy, "Italy", "Iran", "India", "South Sudan", 1
        )
        questions.add(quest1)

        val quest2 = Question(
            2, "What country does this flag belong?",
            R.drawable.brazil, "Brazil", "Mexico", "Portugal", "Argentina", 1
        )
        questions.add(quest2)

        val quest3 = Question(
            3, "What country does this flag belong?",
            R.drawable.germany, "Belgium", "Germany", "Austria", "Netherlands", 2
        )
        questions.add(quest3)

        val quest4 = Question(
            4, "What country does this flag belong?",
            R.drawable.romania, "Chad", "Romania", "Moldova", "Ukraine", 2
        )
        questions.add(quest4)

        val quest5 = Question(
            5, "What country does this flag belong?",
            R.drawable.france, "France", "Italy", "Russia", "Luxembourg", 1
        )
        questions.add(quest5)

        val quest6 = Question(
            6, "What country does this flag belong?",
            R.drawable.nageria, "Kenya", "South Africa", "Nigeria", "Ghana", 3
        )
        questions.add(quest6)

        val quest7 = Question(
            7, "What country does this flag belong?",
            R.drawable.india, "India", "Pakistan", "Bangladesh", "Sri Lanka", 1
        )
        questions.add(quest7)

        val quest8 = Question(
            8, "What country does this flag belong?",
            R.drawable.finland, "Norway", "Sweden", "Finland", "Denmark", 3
        )
        questions.add(quest8)

        val quest9 = Question(
            9, "What country does this flag belong?",
            R.drawable.spain, "Spain", "Portugal", "Italy", "France", 1
        )
        questions.add(quest9)

        val quest10 = Question(
            10, "What country does this flag belong?",
            R.drawable.argentina, "Argentina", "Brazil", "Uruguay", "Chile", 1
        )
        questions.add(quest10)

        return questions
    }
}