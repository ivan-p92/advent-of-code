package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.Point
import java.util.*

object Day16 {

    fun problem16a(input: String): Int {
        val grid = initializeGrid(input)
        return energizeGrid(grid, Point(0, 0) to EAST)
    }

    fun problem16b(input: String): Int {
        val (width, height) = input.lines().let {
            it.first().length to it.size
        }
        val grid = initializeGrid(input)
        val startConfigurations = mutableListOf<Pair<Point, Direction>>()
        for (y in 0..<height) {
            startConfigurations += Point(0, y) to EAST
            startConfigurations += Point(width - 1, y) to WEST
        }
        for (x in 0..<width) {
            startConfigurations += Point(x, 0) to SOUTH
            startConfigurations += Point(x, height - 1) to NORTH
        }
        return startConfigurations.maxOf { startConfiguration ->
            grid.values.forEach { it.visitedDirections.clear() }
            energizeGrid(grid, startConfiguration)
        }
    }

    private fun energizeGrid(grid: Map<Point, Tile>, startConfiguration: Pair<Point, Direction>): Int {
        val queue = LinkedList<Pair<Point, Direction>>()
        queue.push(startConfiguration)

        while (queue.isNotEmpty()) {
            val (point, direction) = queue.pop()
            grid[point]?.let { tile ->
                if (direction !in tile.visitedDirections) {
                    tile.visitedDirections += direction
                    val (firstDirection, secondDirection) = tile.type.transition(direction)
                    queue.push(point.move(firstDirection) to firstDirection)
                    if (secondDirection != null) {
                        queue.push(point.move(secondDirection) to secondDirection)
                    }
                }
            }
        }
        return grid.values.count { it.visitedDirections.isNotEmpty() }
    }

    private fun initializeGrid(input: String): Map<Point, Tile> {
        val grid = mutableMapOf<Point, Tile>()
        input.lines().forEachIndexed { y: Int, line: String ->
            line.forEachIndexed { x, char ->
                grid[Point(x, y)] = Tile(Tile.Type[char])
            }
        }
        return grid
    }

    private data class Tile(val type: Type, val visitedDirections: MutableSet<Direction> = mutableSetOf()) {
        enum class Type(val label: Char, val transition: (Direction) -> Pair<Direction, Direction?>) {
            EMPTY('.', { it to null }),
            VERTICAL_SPLITTER('|', {
                when (it) {
                    EAST, WEST -> NORTH to SOUTH
                    else -> it to null
                }
            }),
            HORIZONTAL_SPLITTER('-', {
                when (it) {
                    NORTH, SOUTH -> EAST to WEST
                    else -> it to null
                }
            }),
            FORWARD_MIRROR('/', {
                when (it) {
                    EAST, WEST -> it.turnLeft() to null
                    else -> it.turnRight() to null
                }
            }),
            BACKWARD_MIRROR('\\', {
                when (it) {
                    EAST, WEST -> it.turnRight() to null
                    else -> it.turnLeft() to null
                }
            });

            companion object {

                private val pipesByLabel = Type.entries.associateBy { it.label }
                operator fun get(label: Char): Type = pipesByLabel[label]!!
            }
        }
    }
}
