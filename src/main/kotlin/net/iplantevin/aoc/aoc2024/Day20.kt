package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.shortestPath
import net.iplantevin.aoc.common.toMapGrid

object Day20 {

    fun part1(input: String, minimumCostSaving: Int = 100): Int = solve(input, minimumCostSaving, 2)

    fun part2(input: String): Int = solve(input, 100, 20)

    private fun solve(input: String, minimumCostSaving: Int, cheatSteps: Int): Int {
        val maze = parseMaze(input)
        val pathResult = shortestPath(
            start = maze.start,
            end = { it == maze.end },
            neighbours = { it.adjacentPoints().filter { p -> p !in maze.walls } }
        )
        val path = pathResult.getVertices()

        return path.withIndex().sumOf { (index, cheatStart) ->
            path.drop(index + 1).count { cheatEnd ->
                val distance = cheatStart.distance(cheatEnd)
                distance <= cheatSteps && pathResult.getCost(cheatEnd) - distance - index >= minimumCostSaving
            }
        }
    }

    private fun parseMaze(input: String): Maze {
        var start = Point(0, 0)
        var end = Point(0, 0)
        val walls = mutableSetOf<Point>()
        input.toMapGrid { point, char ->
            when (char) {
                '#' -> walls += point
                'S' -> start = point
                'E' -> end = point
            }
        }
        return Maze(walls, start, end)
    }

    private data class Maze(val walls: Set<Point>, val start: Point, val end: Point)
}
