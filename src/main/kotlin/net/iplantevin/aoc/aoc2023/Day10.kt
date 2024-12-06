package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.ArrayGrid
import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.*
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toCharArrayGrid

object Day10 {

    // The startDirection should result in a counter-clockwise walk along the pipeline
    fun problem10a(input: String, startDirection: Direction): Int {
        val startPoint = findStartPoint(input)
        val grid = input.toCharArrayGrid()
        val pipelinePoints = walkPipeline(grid, startPoint, startDirection)
        return pipelinePoints.size / 2
    }

    fun problem10b(input: String, startDirection: Direction): Int {
        val startPoint = findStartPoint(input)
        val grid = input.toCharArrayGrid()
        val pipelinePoints = walkPipeline(grid, startPoint, startDirection)
        val enclosedPoints = findEnclosedPoints(grid, startPoint, startDirection, pipelinePoints)
//        printEnclosedPoints(grid, enclosedPoints)
        return enclosedPoints.size
    }

    private fun walkPipeline(grid: ArrayGrid<Char>, startPoint: Point, startDirection: Direction): Set<Point> {
        val pipelinePoints = mutableSetOf(startPoint)
        var nextDirection = startDirection
        var currentPoint = startPoint
        var nextPipe: Pipe
        while (true) {
            currentPoint = currentPoint.move(nextDirection)
            pipelinePoints += currentPoint
            nextPipe = Pipe[grid[currentPoint.yInt][currentPoint.xInt]]
            nextDirection = nextPipe.transition(nextDirection)

            if (nextPipe == Pipe.START) return pipelinePoints
        }
    }

    private fun findEnclosedPoints(
        grid: ArrayGrid<Char>,
        startPoint: Point,
        startDirection: Direction,
        pipelinePoints: Set<Point>,
    ): Set<Point> {
        val enclosedPoints = mutableSetOf<Point>()
        var nextDirection = startDirection
        var currentPoint = startPoint
        var nextPipe: Pipe
        while (true) {
            currentPoint = currentPoint.move(nextDirection)
            nextPipe = Pipe[grid[currentPoint.yInt][currentPoint.xInt]]
            // A bending pipe will change directions, so we first check based on original direction
            enclosedPoints += currentPoint.adjacentPointsInDirection(grid, nextDirection, pipelinePoints)
            nextDirection = nextPipe.transition(nextDirection)
            // And then in the new direction
            enclosedPoints += currentPoint.adjacentPointsInDirection(grid, nextDirection, pipelinePoints)

            if (nextPipe == Pipe.START) return enclosedPoints
        }
    }

    private fun Point.adjacentPointsInDirection(
        grid: ArrayGrid<Char>,
        direction: Direction,
        pipelinePoints: Set<Point>
    ): List<Point> {
        // We assume a counter-clockwise walk, so the "inside" of the loop is always on our left
        // hand side of the given direction.
        val inspectDirection = direction.turnLeft()
        val enclosedPoints = mutableListOf<Point>()
        var currentPipe: Char
        var currentPoint = this
        while (true) {
            currentPoint = currentPoint.move(inspectDirection)
            currentPipe = grid[currentPoint.yInt][currentPoint.xInt]
            if (currentPipe == '.' || currentPoint !in pipelinePoints) enclosedPoints += currentPoint
            else return enclosedPoints
        }
    }

    private fun printEnclosedPoints(grid: ArrayGrid<Char>, enclosedPoints: Set<Point>) {
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
