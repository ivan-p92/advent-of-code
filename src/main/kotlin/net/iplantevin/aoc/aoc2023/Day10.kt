package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.Point

object Day10 {

    fun problem10a(input: String, startDirection: Direction): Int {
        val startPoint = findStart(input)
        val grid = input.lines().map { it.toList() }
        val loopLength = walkPipeline(grid, startPoint, startDirection)
        return loopLength / 2
    }

    private fun walkPipeline(grid: List<List<Char>>, startPoint: Point, startDirection: Direction): Int {
        var nextDirection = startDirection
        var currentPoint = startPoint
        var steps = 0
        var nextPipe: Pipe
        do {
            steps++
            currentPoint = currentPoint.move(nextDirection)
            nextPipe = Pipe[grid[currentPoint.y][currentPoint.x]]
            nextDirection = nextPipe.transition(nextDirection)
        } while (grid[currentPoint.y][currentPoint.x] != 'S')
        return steps
    }

    private fun findStart(input: String): Point {
        val startIndex = input.indexOf('S')
        // We add 1 for the newline characters on each line
        val width = input.lines().first().length + 1
        val startPoint = Point(startIndex % width, startIndex / width)
        return startPoint
    }

    private enum class Pipe(val label: Char, val transition: (Direction) -> Direction) {
        VERTICAL('|', { it }),
        HORIZONTAL('-', { it }),
        NORTH_EAST_BEND('L', { if (it == SOUTH) EAST else NORTH }),
        NORTH_WEST_BEND('J', { if (it == SOUTH) WEST else NORTH }),
        SOUTH_WEST_BEND('7', { if (it == EAST) SOUTH else WEST }),
        SOUTH_EAST_BEND('F', { if (it == NORTH) EAST else SOUTH }),
        START('S', { it });

        companion object {
            private val pipesByLabel = entries.associateBy { it.label }

            operator fun get(label: Char): Pipe = pipesByLabel[label]!!
        }
    }
}