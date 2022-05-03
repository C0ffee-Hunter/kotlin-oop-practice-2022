package main.kotlin.lab4

import java.io.File

enum class Cells(private val textValue: String)
{
    WALL("#"),
    EMPTY("-"),
    PLAYER("P"),
    FINISH("E");

    override fun toString(): String = textValue;
}

enum class State(private val textValue: String) {
    WAIT_MOVE("Waiting for move..."),
    FINISH_GAME("Congratulations. Game finish");

    override fun toString(): String = textValue;
}

enum class Move(private val textValue: String)
{
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right");

    override fun toString(): String = textValue;
}

fun readMaze(): Map<Pair<Int, Int>, Char>
{
    val input = File("C:\\Users\\rogin\\Рабочий стол\\kotlin\\src\\main\\kotlin\\lab4\\maze.txt").readLines().withIndex().flatMap { indexedValue ->
                val xCord = indexedValue.index
                indexedValue.value.toCharArray().withIndex().map { indexedChar ->
                    val yCord = indexedChar.index
                    (xCord to yCord) to indexedChar.value
                }
            }
            .toMap()
        return input
}

class model
{
    val field = readMaze().toMutableMap()
    var state: State = State.WAIT_MOVE

    fun doMove(move: Move)
    {

    }
}