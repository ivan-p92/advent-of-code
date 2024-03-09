package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Point
import java.util.*

object Day17 {

    fun problem17a(input: String): Int = minimumHeatLoss(input, 1, 3)

    fun problem17b(input: String): Int = minimumHeatLoss(input, 4, 10)

    private fun minimumHeatLoss(input: String, minSteps: Int, maxSteps: Int): Int {
        val (width, height) = input.lines().let { it.first().length to it.size }
        val grid = initializeGrid(input)
        val endPoint = Point(width - 1, height - 1)
        val queue = PriorityQueue<CrucibleState>()
        val visited = mutableSetOf<Pair<Point, Direction>>()

        listOf(EAST, SOUTH).forEach {
            queue += CrucibleState(
                heatLoss = 0,
                point = Point(0, 0),
                direction = it,
            )
        }

        while (queue.isNotEmpty()) {
            val state = queue.poll()

            if (state.point == endPoint) {
                return state.heatLoss
            }

            if (state.point to state.direction !in visited) {
                visited += state.point to state.direction
                queue.addAll(nextPossibleStates(state, grid, minSteps, maxSteps))
            }
        }
        return 0
    }

    private fun nextPossibleStates(
        state: CrucibleState,
        grid: Map<Point, Int>,
        minSteps: Int,
        maxSteps: Int
    ): List<CrucibleState> {
        val newStates = mutableListOf<CrucibleState>()
        val newDirections = listOf(state.direction.turnLeft(), state.direction.turnRight())

        for (direction in newDirections) {
            var heatLoss = state.heatLoss
            for (steps in 1..maxSteps) {
                val nextLocation = state.point + direction.delta * steps
                if (nextLocation in grid) {
                    heatLoss += grid[nextLocation]!!
                    if (steps >= minSteps) {
                        newStates += CrucibleState(
                            heatLoss = heatLoss,
                            point = nextLocation,
                            direction = direction,
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

    private fun initializeGrid(input: String): Map<Point, Int> {
        val grid = mutableMapOf<Point, Int>()
        input.lines().forEachIndexed { y: Int, line: String ->
            line.forEachIndexed { x, char ->
                grid[Point(x, y)] = char.digitToInt()
            }
        }
        return grid
    }

    data class CrucibleState(
        val heatLoss: Int,
        val point: Point,
        val direction: Direction,
    ) : Comparable<CrucibleState> {
        override fun compareTo(other: CrucibleState): Int = heatLoss.compareTo(other.heatLoss)

    }
}
