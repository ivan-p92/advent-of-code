package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid

object Day7 {

    fun part1(input: String): Int {
        val (start, manifold) = parse(input)
        val visitedPoints = mutableSetOf<Point>()
        return countSplits(start, manifold, visitedPoints)
    }

    fun part2(input: String): Long {
        val (start, manifold) = parse(input)
        val pathCounts = mutableMapOf<Point, Long>()
        return countPaths(start, manifold, pathCounts)
    }

    private fun countSplits(
        current: Point,
        manifold: MapGrid<Char>,
        visitedPoints: MutableSet<Point>
    ): Int {
        val next = current.move(SOUTH)
        if (current in visitedPoints || next !in manifold) {
            return 0
        }
        visitedPoints += current
        return if (manifold[next] == '^') {
            1 + countSplits(next.move(WEST), manifold, visitedPoints) +
                    countSplits(next.move(EAST), manifold, visitedPoints)
        } else {
            countSplits(next, manifold, visitedPoints)
        }
    }

    private fun countPaths(
        current: Point,
        manifold: MapGrid<Char>,
        pathCounts: MutableMap<Point, Long>
    ): Long {
        val next = current.move(SOUTH)
        if (next !in manifold) {
            return 1
        } else if (next !in pathCounts) {
            if (manifold[next] == '^') {
                pathCounts[next] =
                    countPaths(next.move(WEST), manifold, pathCounts) +
                            countPaths(next.move(EAST), manifold, pathCounts)
            } else {
                pathCounts[next] = countPaths(next, manifold, pathCounts)
            }
        }
        return pathCounts[next]!!
    }

    private fun parse(input: String): Pair<Point, MapGrid<Char>> {
        var start = Point(0, 0)
        val grid = input.toMapGrid { point, c -> c.also { if (c == 'S') start = point } }
        return start to grid
    }
}
