package lab4.controller

import main.kotlin.lab4.GAME_NOT_FINISHED
import main.kotlin.lab4.State
import main.kotlin.lab4.Move
import main.kotlin.lab4.Model

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state in GAME_NOT_FINISHED) {
            val input = readln()
            var movent: Move = Move.Wait
            for (i in input) {
                when (i) {
                    'w' -> movent = Move.Up
                    'a' -> movent = Move.Left
                    's' -> movent = Move.Down
                    'd' -> movent = Move.Right
                    'q' -> movent = Move.EXIT
                    '1' -> movent = State.Save
                }
                try {
                    model.doMove(state)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            if(state == State.EXIT)
                break ///Грубый метод
        }
    }
}
