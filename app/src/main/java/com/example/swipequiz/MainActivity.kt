package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = ArrayList<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private val CORRECT_MESSAGE = "Correct! The message was "
    private val INCORRECT_MESSAGE = "Incorrect :( The message was "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWER[i]))
        }
        questionAdapter.notifyDataSetChanged()


    }

    /**
     * Makes it possible to recognise swiping left and right.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        //Creates TouchHelper object for swiping to the left and right.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = questions.get(position)

                getSwipeResult(direction, question)
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        return ItemTouchHelper(callback)
    }

    /**
     * Checks if the swipe direction equals the answer and shows a message.
     *
     * @param direction - Swipe direction
     * @param question - The question from the list with questions
     */
    private fun getSwipeResult(direction: Int, question: Question) {
        var message = ""
        if (direction == ItemTouchHelper.RIGHT) {
            if (question.answer) {
                message = CORRECT_MESSAGE
            } else {
                message = INCORRECT_MESSAGE
            }
        } else {
            if (direction == ItemTouchHelper.LEFT) {
                if (!question.answer) {
                    message = CORRECT_MESSAGE
                } else {
                    message = INCORRECT_MESSAGE
                }
            }
        }
        Toast.makeText(this, message + question.answer, Toast.LENGTH_LONG).show()
    }
}
