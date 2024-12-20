package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Move
import net.iplantevin.aoc.common.PathResult
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.shortestPath
import net.iplantevin.aoc.common.toMapGrid

object Day16 {

    fun part1(input: String): Int = solve2(input).cost

    fun part2(input: String): Int = solve2(input).let { path ->
        findAllPointsOnBestPaths(path.end!!, path.possiblePaths)
    }.size

    private fun solve2(input: String): PathResult<Move> {
        val maze = parseMaze(input)
        val path = shortestPath(
            start = Move(maze.start, EAST),
            end = {it.position == maze.end},
            neighbours = { findNeighbours(it, maze) },
            cost = {a, b -> if (a.position == b.position) 1000 else 1}
        )
        return path
    }

    private fun findNeighbours(move: Move, maze: Maze): List<Move> {
        return listOf(move.nextMove(), move.turnLeft(), move.turnRight()).filter { it.position !in maze.walls }
    }

    private fun findAllPointsOnBestPaths(current: Move, possiblePaths: Map<Move, Set<Move>>): Set<Point> {
        return buildSet {
            add(current.position)
            possiblePaths[current].orEmpty().forEach { previous ->
                addAll(findAllPointsOnBestPaths(previous, possiblePaths))
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
