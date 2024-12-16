package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Move
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid
import java.util.*
import kotlin.math.min

object Day16 {

    fun part1(input: String): Long {
        val maze = parseMaze(input)
        val queue = PriorityQueue<State>()
        val initialState = State(Move(maze.start, EAST), 0, moves = emptyList())
        val consideredMoves = mutableSetOf(initialState.move)
        queue += initialState
        while (queue.isNotEmpty()) {
            val state = queue.poll()
            if (state.move.position == maze.end) {
                return state.cost
            }
            findNextStates(state, maze).forEach { nextState ->
                if (nextState.move !in consideredMoves) {
                    consideredMoves += nextState.move
                    queue += nextState
                }
            }
        }
        return 0
    }

    fun part2(input: String): Int {
        val maze = parseMaze(input)
        val queue = PriorityQueue<State>()
        val initialState = State(Move(maze.start, EAST), 0, moves = emptyList())
        val consideredMoves = mutableMapOf(initialState.move to 0L)
        val paths = mutableListOf<Pair<Set<Point>, Long>>()
        var lowestCost = Long.MAX_VALUE
        queue += initialState
        while (queue.isNotEmpty()) {
            val state = queue.poll()
            if (state.move.position == maze.end) {
                paths += Pair(state.moves.mapTo(mutableSetOf()) { it.position }, state.cost)
                lowestCost = min(lowestCost, state.cost)
            }
            findNextStates(state, maze).forEach { nextState ->
                if (nextState.move !in consideredMoves || consideredMoves[nextState.move]!! == nextState.cost) {
                    consideredMoves += nextState.move to nextState.cost
                    queue += nextState
                }
            }
        }
        val positions = mutableSetOf<Point>()
        paths.filter { (_, cost) -> cost == lowestCost }.forEach { (pos, _) -> positions += pos }
        return positions.size
    }

    private fun findNextStates(state: State, maze: Maze): List<State> {
        return listOf(
            state.move.nextMove() to 1,
            state.move.turnLeft() to 1000,
            state.move.turnRight() to 1000
        ).mapNotNull { (move, cost) ->
            if (move.position !in maze.walls) {
                State(move, state.cost + cost, state.moves + move)
            } else null
        }
    }

    private fun parseMaze(input: String): Maze {
        var start = Point(0, 0)
        var end = Point(0, 0)
        val walls = mutableSetOf<Point>()
        input.toMapGrid { point, char ->
            when (char) {
                '#' -> walls += point
                'S' -> start = point
                'E' -> end = point
            }
        }
        return Maze(walls, start, end)
    }

    private data class Maze(val walls: Set<Point>, val start: Point, val end: Point)

    private data class State(
        val move: Move,
        val cost: Long,
        val moves: List<Move>
    ) : Comparable<State> {

        override fun compareTo(other: State): Int = cost.compareTo(other.cost)
    }
}
