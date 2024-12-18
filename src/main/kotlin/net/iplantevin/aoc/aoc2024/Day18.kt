package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Move
import net.iplantevin.aoc.common.Point
import java.util.*

object Day18 {

    fun part1(input: String, endPoint: Point, maxBytes: Int): Int {
        val bytes = parse(input).take(maxBytes)
        return solve(endPoint, bytes, maxBytes)!!.cost
    }

    fun part2(input: String, endPoint: Point, startBytes: Int): String {
        val bytes = parse(input)
        var maxBytes = startBytes
        var endState = solve(endPoint, bytes, maxBytes)
        while (endState != null) {
            maxBytes++
            endState = solve(endPoint, bytes, maxBytes)
        }
        return bytes[maxBytes - 1].let { "${it.x},${it.y}" }
    }

    private fun solve(endPoint: Point, allBytes: List<Point>, maxBytes: Int): State? {
        val bytes = allBytes.take(maxBytes)
        val start = Point(0, 0)
        val startStates = listOf(
            State(0, Move(start, EAST), listOf(start)),
            State(0, Move(start, SOUTH), listOf(start))
        )
        val queue = PriorityQueue<State>()
        queue += startStates
        val consideredMoves = mutableSetOf(startStates.first().move, startStates.last().move)
        var endState: State? = null
        while (endState == null && queue.isNotEmpty()) {
            val state = queue.poll()
            if (state.move.position == endPoint) {
                endState = state
                break
            }
            Direction.entries.forEach { direction ->
                val nextMove = Move(state.move.position.move(direction), direction)
                if (nextMove !in consideredMoves && nextMove.position in endPoint && nextMove.position !in bytes) {
                    queue += State(state.cost + 1, nextMove, state.path + nextMove.position)
                    consideredMoves += nextMove
                }
            }
        }
        return endState
    }

    private fun parse(input: String): List<Point> = input.lines().map { line ->
        val (x, y) = line.split(",")
        Point(x.toLong(), y.toLong())
    }

    private data class State(val cost: Int, val move: Move, val path: List<Point>) : Comparable<State> {
        override fun compareTo(other: State): Int = cost.compareTo(other.cost)
    }
}
