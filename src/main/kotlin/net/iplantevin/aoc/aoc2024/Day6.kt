package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Move
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid

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
        grid: MapGrid<Char>,
        start: Point
    ): Pair<Int, Set<Move>> {
        val visitedPositions = mutableSetOf<Point>()
        val visitedPositionsWithDirection = mutableSetOf<Move>()
        var currentPosition = start
        var direction = NORTH
        do {
            visitedPositions += currentPosition
            visitedPositionsWithDirection += Move(currentPosition, direction)
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
        grid: MapGrid<Char>,
        obstructions: MutableSet<Point>,
        start: Point
    ): Int {
        var loopObstructions = 0
        for (obstruction in obstructions) {
            val pastMoves = mutableSetOf<Move>()
            var currentMove = Move(start, NORTH)
            do {
                pastMoves += currentMove
                val nextPosition = currentMove.nextMove().position
                currentMove = if (grid[nextPosition] == '#' || nextPosition == obstruction) {
                    currentMove.turnRight()
                } else {
                    currentMove.nextMove()
                }
                if (currentMove in pastMoves) {
                    loopObstructions++
                    break
                }
            } while (currentMove.position in grid)
        }
        return loopObstructions
    }

    private fun initializeGrid(input: String): Pair<MapGrid<Char>, Point> {
        var startPoint = Point(0, 0)
        val grid = input.toMapGrid { point, char ->
            if (char == '^') startPoint = point
            char
        }
        return grid to startPoint
    }
}
