package com.sony.tictactoygame

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {

        val buClicked = view as Button
        var cellId = 0
        when (buClicked.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
//        Toast.makeText(this, "ID:" + cellId, Toast.LENGTH_LONG).show()

        playGame(cellId, buClicked)

    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer: Int = 1

    fun playGame(cellId: Int, buClicked: Button) {
        if (activePlayer == 1) {
            buClicked.text = "x"
            buClicked.setBackgroundResource(R.color.player1)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            buClicked.text = "0"
            buClicked.setBackgroundResource(R.color.player2)
            player2.add(cellId)
            activePlayer = 1
        }

        buClicked.isEnabled = false

        checkTheWinner()

    }

    fun checkTheWinner() {
        var winner = -1

//        row=1

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

//        row=2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

//        row=3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

//        col=1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
//        col=2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
//        col=3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
//        diag=1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
//        diag=2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "Player 1 Win the Game", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Player 2 Win the Game", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun autoPlay() {
        try {
            var emptyCell = ArrayList<Int>()
            for (cellID in 1..9) {
                if (!(player1.contains(cellID) || player2.contains(cellID))) {
                    emptyCell.add(cellID)
                }
            }


            val r = Random
            val randIndex = r.nextInt(emptyCell.size - 0) + 0
            val cellID = emptyCell[randIndex]


            var buSelect: Button?
            when (cellID) {
                1 -> buSelect = button1
                2 -> buSelect = button2
                3 -> buSelect = button3
                4 -> buSelect = button4
                5 -> buSelect = button5
                6 -> buSelect = button6
                7 -> buSelect = button7
                8 -> buSelect = button8
                9 -> buSelect = button9

                else -> buSelect = button1

            }

            playGame(cellID, buSelect)
        } catch (e: Exception) {

        }
    }


}

