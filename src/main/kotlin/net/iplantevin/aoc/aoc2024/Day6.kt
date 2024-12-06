package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Point

object Day6 {

    fun problem6a(input: String): Int {
        val (grid, start) = initializeGrid(input)
        val (totalVisitedPositions, _) = walkAround(grid, start)
        return totalVisitedPositions
    }

    fun problem6b(input: String): Int {
        val (grid, start) = initializeGrid(input)
        val (_, defaultPositions) = walkAround(grid, start)
        val obstructions = defaultPositions.mapTo(mutableSetOf()) { (position, direction) ->
            position + direction.delta
        }.also { it.remove(start) }
        println("Going to place ${obstructions.size} obstructions")
        return countObstructionsThatCauseLoops(grid, obstructions, start)
    }

    private fun walkAround(
        grid: Map<Point, Char>,
        start: Point
    ): Pair<Int, Set<Pair<Point, Direction>>> {
        val visitedPositions = mutableSetOf<Point>()
        val visitedPositionsWithDirection = mutableSetOf<Pair<Point, Direction>>()
        var currentPosition = start
        var direction = Direction.NORTH
        do {
            visitedPositions += currentPosition
            visitedPositionsWithDirection += currentPosition to direction
            val nextPosition = currentPosition + direction.delta
            if (grid[nextPosition] == '#') {
                direction = direction.turnRight()
            } else {
                currentPosition = nextPosition
            }
        } while (currentPosition in grid)
        return visitedPositions.size to visitedPositionsWithDirection
    }

    private fun countObstructionsThatCauseLoops(
        grid: Map<Point, Char>,
        obstructions: MutableSet<Point>,
        start: Point
    ): Int {
        var loopObstructions = 0
        for (obstruction in obstructions) {
            val visitedPositions = mutableSetOf<Pair<Point, Direction>>()
            var currentPosition = start
            var direction = Direction.NORTH
            do {
                visitedPositions += currentPosition to direction
                val nextPosition = currentPosition + direction.delta
                if (grid[nextPosition] == '#' || nextPosition == obstruction) {
                    direction = direction.turnRight()
                } else {
                    currentPosition = nextPosition
                }
                if (currentPosition to direction in visitedPositions) {
                    loopObstructions++
                    break
                }
            } while (currentPosition in grid)
        }
        return loopObstructions
    }

    private fun initializeGrid(input: String): Pair<Map<Point, Char>, Point> {
        val grid = mutableMapOf<Point, Char>()
        var startPoint = Point(0, 0)
        input.lines().forEachIndexed { y: Int, line: String ->
            line.forEachIndexed { x, char ->
                if (char == '^') startPoint = Point(x, y)
                grid[Point(x, y)] = char
            }
        }
        return grid to startPoint
    }
}
