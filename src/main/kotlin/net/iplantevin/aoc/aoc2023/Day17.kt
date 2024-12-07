package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.*
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.SOUTH
import java.util.*

object Day17 {

    fun part1(input: String): Int = minimumHeatLoss(input, 1, 3)

    fun part2(input: String): Int = minimumHeatLoss(input, 4, 10)

    private fun minimumHeatLoss(input: String, minSteps: Int, maxSteps: Int): Int {
        val (width, height) = input.dimensions()
        val grid = input.toMapGrid { char -> char.digitToInt() }
        val endPoint = Point(width - 1, height - 1)
        val queue = PriorityQueue<CrucibleState>()
        val pastMoves = mutableSetOf<Move>()

        listOf(EAST, SOUTH).forEach {
            queue += CrucibleState(
                heatLoss = 0,
                move = Move(Point(0, 0), it)
            )
        }

        while (queue.isNotEmpty()) {
            val state = queue.poll()

            if (state.move.position == endPoint) {
                return state.heatLoss
            }

            if (state.move !in pastMoves) {
                pastMoves += state.move
                queue.addAll(nextPossibleStates(state, grid, minSteps, maxSteps))
            }
        }
        return 0
    }

    private fun nextPossibleStates(
        state: CrucibleState,
        grid: MapGrid<Int>,
        minSteps: Int,
        maxSteps: Int
    ): List<CrucibleState> {
        val newStates = mutableListOf<CrucibleState>()
        val newDirections = listOf(state.move.direction.turnLeft(), state.move.direction.turnRight())

        for (direction in newDirections) {
            var heatLoss = state.heatLoss
            for (steps in 1..maxSteps) {
                val nextMove = Move(state.move.position, direction, steps).nextMove()
                if (nextMove.position in grid) {
                    heatLoss += grid[nextMove.position]!!
                    if (steps >= minSteps) {
                        newStates += CrucibleState(
                            heatLoss = heatLoss,
                            move = nextMove.copy(stepSize = 1)
                        )
                    }
                } else {
                    // Moving out of bounds
                    break
                }
            }
        }
        return newStates
    }

    data class CrucibleState(
        val heatLoss: Int,
        val move: Move
    ) : Comparable<CrucibleState> {
        override fun compareTo(other: CrucibleState): Int = heatLoss.compareTo(other.heatLoss)

    }
}
