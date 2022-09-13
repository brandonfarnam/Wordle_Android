package com.example.wordle_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wordle_2.FourLetterWordList.getRandomFourLetterWord
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val targetWord = getRandomFourLetterWord()
        var j = 1
        var count = 1
        val temp = "output"
        val button = findViewById<Button>(R.id.button3)
        val result = findViewById<TextView>(R.id.result)

        button.setOnClickListener {
            val textView = findViewById<EditText>(R.id.userInput)
            val strValue = textView.text.toString()
            val output = checkGuess(targetWord, strValue)
            val inputId = temp + j.toString()
            var resId = resources.getIdentifier(inputId, "id", getPackageName())
            val textViewInput = findViewById<TextView>(resId)
            textViewInput.text = strValue
            j += 1
            val outputId = temp + j.toString()
            resId = resources.getIdentifier(outputId, "id", getPackageName())
            val textViewOutput = findViewById<TextView>(resId)
            textViewOutput.text = output
            j += 1
            if (output == "OOOO") {
                Toast.makeText(getApplicationContext(), "Congratulation!", Toast.LENGTH_SHORT).show()
                button.visibility = View.INVISIBLE
                result.text = targetWord
            }
            if (count == 3) {
                Toast.makeText(getApplicationContext(), "You Lose!", Toast.LENGTH_SHORT).show()
                button.visibility = View.INVISIBLE
                result.text = targetWord

            }
            count += 1
        }

//        Toast.makeText(getApplicationContext(), "You lose, please try again!", Toast.LENGTH_SHORT).show()
//        val guess = findViewById<Button>(R.id.button3)
//        guess.visibility = View.INVISIBLE
    }



    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}