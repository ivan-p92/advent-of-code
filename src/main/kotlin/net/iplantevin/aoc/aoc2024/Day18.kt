package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.PathResult
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.shortestPath

object Day18 {

    fun part1(input: String, endPoint: Point, maxBytes: Int): Int {
        val bytes = parse(input).take(maxBytes)
        return solve2(endPoint, bytes, maxBytes).cost
    }

    fun part2(input: String, endPoint: Point, startBytes: Int): String {
        val bytes = parse(input)
        var maxBytes = startBytes
        var path = solve2(endPoint, bytes, maxBytes)
        while (path.end != null) {
            // We only need to find a new path when a byte "falls" that lies on the last found path.
            while (bytes[maxBytes - 1] !in path.getVertices()) {
                maxBytes++
            }
            path = solve2(endPoint, bytes, maxBytes)
        }
        return bytes[maxBytes - 1].let { "${it.x},${it.y}" }
    }

    private fun solve2(endPoint: Point, allBytes: List<Point>, maxBytes: Int): PathResult<Point> {
        val bytes = allBytes.take(maxBytes)
        return shortestPath(
            start = Point(0, 0),
            end = { it == endPoint },
            neighbours = { it.adjacentPoints().filter { next -> next in endPoint && next !in bytes } }
        )
    }

    private fun parse(input: String): List<Point> = input.lines().map { line ->
        val (x, y) = line.split(",")
        Point(x.toLong(), y.toLong())
    }
}
