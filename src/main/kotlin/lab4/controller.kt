package lab4.controller

import main.kotlin.lab4.State
import main.kotlin.lab4.Move
import main.kotlin.lab4.Model

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state != State.FINISH_GAME) {
            val input = readln()
            var movement: Move = Move.Wait
            for (button in input) {
                when (button) {
                    'w' -> movement = Move.Up
                    'a' -> movement = Move.Left
                    's' -> movement = Move.Down
                    'd' -> movement = Move.Right
                }
                try {
                    model.doMove(movement)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            if (model.state == State.FINISH_GAME) break
        }
    }
}
