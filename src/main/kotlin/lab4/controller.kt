package lab4.controller

import main.kotlin.lab4.State
import main.kotlin.lab4.Move
import main.kotlin.lab4.Model

class Controller(private val model: Model) {
    init {
        //startGame()
    }
    private fun startGame() {
        while (model.state != State.FINISH_GAME) {
            val input = readln()
            var movent: Move = Move.Wait
            for (button in input) {
                when (button) {
                    'w' -> movent = Move.Up
                    'a' -> movent = Move.Left
                    's' -> movent = Move.Down
                    'd' -> movent = Move.Right
                }
                try {
                    model.doMove(movent)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            if(model.state == State.FINISH_GAME)
                break ///Грубый метод
        }
    }
}
