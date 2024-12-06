package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.*
import net.iplantevin.aoc.common.Direction.*
import java.util.*

object Day16 {

    fun problem16a(input: String): Int {
        val grid = input.toMapGrid { char -> Tile(Tile.Type[char]) }
        return energizeGrid(grid, Move(Point(0, 0), EAST))
    }

    fun problem16b(input: String): Int {
        val (width, height) = input.dimensions()
        val grid = input.toMapGrid { char -> Tile(Tile.Type[char]) }
        val startConfigurations = mutableListOf<Move>()
        for (y in 0..<height) {
            startConfigurations += Move(Point(0, y), EAST)
            startConfigurations += Move(Point(width - 1, y), WEST)
        }
        for (x in 0..<width) {
            startConfigurations += Move(Point(x, 0), SOUTH)
            startConfigurations += Move(Point(x, height - 1), NORTH)
        }
        return startConfigurations.maxOf { startConfiguration ->
            grid.values.forEach { it.visitedDirections.clear() }
            energizeGrid(grid, startConfiguration)
        }
    }

    private fun energizeGrid(grid: MapGrid<Tile>, startMove: Move): Int {
        val queue = LinkedList<Move>()
        queue.push(startMove)

        while (queue.isNotEmpty()) {
            val move = queue.pop()
            grid[move.position]?.let { tile ->
                if (move.direction !in tile.visitedDirections) {
                    tile.visitedDirections += move.direction
                    val (firstDirection, secondDirection) = tile.type.transition(move.direction)
                    queue.push(Move(move.position.move(firstDirection), firstDirection))
                    if (secondDirection != null) {
                        queue.push(Move(move.position.move(secondDirection), secondDirection))
                    }
                }
            }
        }
        return grid.values.count { it.visitedDirections.isNotEmpty() }
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
