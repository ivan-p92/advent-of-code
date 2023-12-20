package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.Grid
import net.iplantevin.aoc.common.Point

object Day10 {

    fun problem10a(input: String, startDirection: Direction): Int {
        val startPoint = findStartPoint(input)
        val grid = input.lines().map { it.toList() }
        val pipelinePoints = walkPipeline(grid, startPoint, startDirection)
        return pipelinePoints.size / 2
    }

    fun problem10b(input: String, startDirection: Direction): Int {
        val startPoint = findStartPoint(input)
        val grid = input.lines().map { it.toList() }
        val pipelinePoints = walkPipeline(grid, startPoint, startDirection)
        val enclosedPoints = findEnclosedPoints(grid, startPoint, startDirection, pipelinePoints)
//        printEnclosedPoints(grid, enclosedPoints)
        return enclosedPoints.size
    }

    private fun walkPipeline(grid: Grid<Char>, startPoint: Point, startDirection: Direction): Set<Point> {
        val pipelinePoints = mutableSetOf(startPoint)
        var nextDirection = startDirection
        var currentPoint = startPoint
        var nextPipe: Pipe
        do {
            currentPoint = currentPoint.move(nextDirection)
            pipelinePoints += currentPoint
            nextPipe = Pipe[grid[currentPoint.y][currentPoint.x]]
            nextDirection = nextPipe.transition(nextDirection)
        } while (grid[currentPoint.y][currentPoint.x] != 'S')
        return pipelinePoints
    }

    private fun findEnclosedPoints(
        grid: Grid<Char>,
        startPoint: Point,
        startDirection: Direction,
        pipelinePoints: Set<Point>,
    ): Set<Point> {
        val enclosedPoints = mutableSetOf<Point>()
        var nextDirection = startDirection
        var currentPoint = startPoint
        var nextPipe: Pipe
        do {
            currentPoint = currentPoint.move(nextDirection)
            nextPipe = Pipe[grid[currentPoint.y][currentPoint.x]]
            // A bending pipe will change directions, so we first check based on original direction
            enclosedPoints += currentPoint.adjacentPointsInDirection(grid, nextDirection, pipelinePoints)
            nextDirection = nextPipe.transition(nextDirection)
            // And then in the new direction
            enclosedPoints += currentPoint.adjacentPointsInDirection(grid, nextDirection, pipelinePoints)
        } while (grid[currentPoint.y][currentPoint.x] != 'S')
        return enclosedPoints
    }

    private fun Point.adjacentPointsInDirection(
        grid: Grid<Char>,
        direction: Direction,
        pipelinePoints: Set<Point>
    ): List<Point> {
        // We assume a counter-clockwise walk
        val inspectDirection = direction.turnLeft()
        val groundPoints = mutableListOf<Point>()
        var currentPipe: Char
        var currentPoint = this
        while (true) {
            try {
                currentPoint = currentPoint.move(inspectDirection)
                currentPipe = grid[currentPoint.y][currentPoint.x]
                if (currentPipe == '.' || currentPoint !in pipelinePoints) groundPoints += currentPoint
                else return groundPoints
            } catch (e: IndexOutOfBoundsException) {
                return groundPoints
            }
        }
    }

    private fun printEnclosedPoints(grid: Grid<Char>, enclosedPoints: Set<Point>) {
        // Prints a representation of the grid with asterisks for all enclosed points
        grid.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val point = Point(x, y)
                when {
                    point in enclosedPoints -> print('*')
                    else -> print(c)
                }
            }
            println()
        }
        println()
    }

    private fun findStartPoint(input: String): Point {
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
