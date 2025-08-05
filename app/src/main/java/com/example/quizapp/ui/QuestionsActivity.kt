package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewOptionOne: TextView
    private lateinit var textViewOptionTwo: TextView
    private lateinit var textViewOptionThree: TextView
    private lateinit var textViewOptionFour: TextView

    private lateinit var checkButton: Button

    private var questionCounter = 0
    private lateinit var questionList: MutableList<Question>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private var score = 0
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        flagImage = findViewById(R.id.image_flag)
        checkButton = findViewById(R.id.button_check)

        textViewOptionOne = findViewById(R.id.text_view_option_one)
        textViewOptionTwo = findViewById(R.id.text_view_option_two)
        textViewOptionThree = findViewById(R.id.text_view_option_three)
        textViewOptionFour = findViewById(R.id.text_view_option_four)

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName = intent.getStringExtra("userName")
        questionList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionList.size}")

        showNextQuestion()
    }

    private fun showNextQuestion() {
        if (questionCounter >= questionList.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("totalQuestions", questionList.size)
            intent.putExtra("userName", userName)
            startActivity(intent)
            finish()
            return
        }

        resetOptions()

        currentQuestion = questionList[questionCounter]

        flagImage.setImageResource(currentQuestion.image)
        progressBar.progress = questionCounter + 1
        textViewProgress.text = "${questionCounter + 1}/${progressBar.max}"
        textViewQuestion.text = currentQuestion.question
        textViewOptionOne.text = currentQuestion.optionOne
        textViewOptionTwo.text = currentQuestion.optionTwo
        textViewOptionThree.text = currentQuestion.optionThree
        textViewOptionFour.text = currentQuestion.optionFour

        checkButton.text = "CHECK"
        selectedAnswer = 0
        answered = false
    }

    private fun resetOptions() {
        val options = listOf(
            textViewOptionOne,
            textViewOptionTwo,
            textViewOptionThree,
            textViewOptionFour
        )

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_option_one -> selectedOption(textViewOptionOne, 1)
            R.id.text_view_option_two -> selectedOption(textViewOptionTwo, 2)
            R.id.text_view_option_three -> selectedOption(textViewOptionThree, 3)
            R.id.text_view_option_four -> selectedOption(textViewOptionFour, 4)
            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    questionCounter++
                    showNextQuestion()
                }
            }
        }
    }

    private fun selectedOption(textView: TextView, selectOptionNumber: Int) {
        resetOptions()
        selectedAnswer = selectOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun checkAnswer() {
        answered = true

        val correct = currentQuestion.correctAnswer

        val options = listOf(
            textViewOptionOne,
            textViewOptionTwo,
            textViewOptionThree,
            textViewOptionFour
        )

        if (selectedAnswer == correct) {
            options[correct - 1].background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
            score++
        } else {
            options[selectedAnswer - 1].background = ContextCompat.getDrawable(
                this,
                R.drawable.wrong_option_border_bg
            )
            options[correct - 1].background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
        }

        checkButton.text = if (questionCounter == questionList.size - 1) "FINISH" else "NEXT"
    }
}