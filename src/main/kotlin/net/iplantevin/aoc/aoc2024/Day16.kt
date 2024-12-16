package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Move
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid
import java.util.*

object Day16 {

    fun part1(input: String): Int = solve(input).let { (endState, _) -> endState.cost }

    fun part2(input: String): Int = solve(input).let { (endState, bestMoves) ->
        findAllPointsOnBestPaths(endState.move, bestMoves)
    }.size

    private fun solve(input: String): Pair<State, Map<Move, Set<Move>>> {
        val maze = parseMaze(input)
        val queue = PriorityQueue<State>()
        val initialState = State(Move(maze.start, EAST), 0, moves = emptyList())
        val moveCosts = mutableMapOf(initialState.move to 0)
        // Maps from a move to the set of previous moves that all lie on a path with the lowest cost.
        val bestMoves = mutableMapOf<Move, MutableSet<Move>>()
        queue += initialState
        var iterations = 0
        var endState: State? = null

        while (endState == null && queue.isNotEmpty()) {
            iterations++
            val state = queue.poll()
            if (state.move.position == maze.end) {
                endState = state
            }
            findNextStates(state, maze).forEach { nextState ->
                if (nextState.cost < (moveCosts[nextState.move] ?: Int.MAX_VALUE)) {
                    moveCosts[nextState.move] = nextState.cost
                    bestMoves[nextState.move] = mutableSetOf(state.move)
                    queue += nextState
                } else if (moveCosts[nextState.move] == nextState.cost) {
                    bestMoves[nextState.move]!! += state.move
                }
            }
        }
        return endState!! to bestMoves
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

    private fun findAllPointsOnBestPaths(current: Move, bestMoves: Map<Move, Set<Move>>): Set<Point> {
        return buildSet {
            add(current.position)
            bestMoves[current].orEmpty().forEach { previous ->
                addAll(findAllPointsOnBestPaths(previous, bestMoves))
            }
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
        val cost: Int,
        val moves: List<Move>
    ) : Comparable<State> {

        override fun compareTo(other: State): Int = cost.compareTo(other.cost)
    }
}
