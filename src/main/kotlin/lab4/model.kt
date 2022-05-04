package main.kotlin.lab4

import java.io.File

enum class Cells(private val textValue: String)
{
    WALL("#"),
    EMPTY("-"),
    PLAYER("P"),
    FINISH("E");

    override fun toString(): String = textValue
}

enum class State(private val textValue: String) {
    WAIT_MOVE("Waiting for move..."),
    FINISH_GAME("Congratulations. Game finish");

    override fun toString(): String = textValue;
}

enum class Move(private val textValue: String)
{
    Wait("wait"),
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right");

    override fun toString(): String = textValue;
}

val GAME_NOT_FINISHED = setOf(Move.Up, Move.Down, Move.Left, Move.Right, State.WAIT_MOVE)

fun readMaze(): Map<Pair<Int, Int>, Char>
{
    val level = File("C:\\Users\\Даниил\\Desktop\\kotlin-oop-practice-2022-master (1)\\kotlin-oop-practice-2022-master\\" +
            "src\\main\\kotlin\\lab4\\maze.txt").readLines().withIndex().flatMap { indexedValue ->
                val xCord = indexedValue.index
                indexedValue.value.toCharArray().withIndex().map { indexedChar ->
                    val yCord = indexedChar.index
                    (xCord to yCord) to indexedChar.value
                }
            }
            .toMap()
        return level
}

/*val level = File("C:\\Users\\Даниил\\Desktop\\kotlin-oop-practice-2022-master (1)\\kotlin-oop-practice-2022-master\\" +
        "src\\main\\kotlin\\lab4\\maze.txt").readLines()
val LEVEL_COL = level[0].length
val LEVEL_ROW = level.size*/

class Model
{
    //private val _board: MutableList<MutableList<Cells>> = mutableListOf()//initEmptyBoard()
    private var row = 0
    private var col = 0
    //private val board: List<List<Cells>>
        //get() = _board
    val field = readMaze().toMutableMap()
    var move: Move = Move.Wait

    fun doMove(move: Move) {
        var playerRow = row
        var playerCol = col

        when (move) {
            Move.Right -> playerCol++
            Move.Left -> playerCol--
            Move.Up -> playerRow--
            Move.Down -> playerRow++
            //else -> require(move != State.EXIT) { "Game finished" }
        }

        require(_board[playerRow][playerCol] != Cells.WALL) { "Wrong move" }

        val player = _board[playerRow][playerCol]

        _board[row][col] = Cells.EMPTY
        _board[playerRow][playerCol] = Cells.PLAYER

        require(Cells.PLAYER != Cells.FINISH) { "Congratulations. Game finish" }

    }

    fun saveGame(move: Move)
    {

    }


}
