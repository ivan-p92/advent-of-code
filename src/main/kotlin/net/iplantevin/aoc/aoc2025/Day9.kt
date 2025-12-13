package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.distinctPairs
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day9 {

    fun part1(input: String): Long = parse(input).distinctPairs().maxOf(this::area)

    fun part2(input: String): Long {
        val redTiles = parse(input)
        val edges = (redTiles + redTiles.first()).zipWithNext().map { (tile1, tile2) ->
            if (tile1.x == tile2.x) {
                VerticalEdge(tile1, tile2)
            } else {
                HorizontalEdge(tile1, tile2)
            }
        }
        return redTiles.distinctPairs()
            .sortedByDescending(this::area)
            .first { isValidRectangle(it.first, it.second, edges) }
            .let(this::area)
    }

    private fun parse(input: String): List<Point> = input.lines().map { line ->
        line.split(",").let { (x, y) -> Point(x, y) }
    }

    private fun area(oppositeCorners: Pair<Point, Point>): Long = oppositeCorners.let { (c1, c2) ->
        (abs(c1.x - c2.x) + 1) * (abs(c1.y - c2.y) + 1)
    }

    private fun isValidRectangle(tile1: Point, tile2: Point, edges: List<Edge>): Boolean {
        val tile3 = Point(tile1.x, tile2.y)
        val tile4 = Point(tile2.x, tile1.y)

        val min = Point(min(tile1.x, tile2.x), min(tile1.y, tile2.y))
        val max = Point(max(tile1.x, tile2.x), max(tile1.y, tile2.y))

        val topEdge = HorizontalEdge(min.x..max.x, min.y)
        val bottomEdge = HorizontalEdge(min.x..max.x, max.y)
        val leftEdge = VerticalEdge(min.x, min.y..max.y)
        val rightEdge = VerticalEdge(min.x, min.y..max.y)

        val rectangle = listOf(topEdge, bottomEdge, leftEdge, rightEdge)

        return tile3.isWithinPolygon(edges)
                && tile4.isWithinPolygon(edges)
                && rectangle.none { rectangleEdge ->
            when (rectangleEdge) {
                is HorizontalEdge -> edges.any { it is VerticalEdge && rectangleEdge intersectsWith it }
                is VerticalEdge -> edges.any { it is HorizontalEdge && rectangleEdge intersectsWith it }
            }
        }
    }

    private sealed interface Edge {
        operator fun contains(point: Point): Boolean
    }

    private data class VerticalEdge(val x: Long, val y: LongRange) : Edge {

        val intermediateY = (y.first + 1)..y.last

        constructor(tile1: Point, tile2: Point) : this(tile1.x, min(tile1.y, tile2.y)..<max(tile1.y, tile2.y))

        infix fun intersectsWith(horizontalEdge: HorizontalEdge): Boolean =
            x in horizontalEdge.intermediateX && horizontalEdge.y in intermediateY

        override fun contains(point: Point): Boolean = point.x == x && point.y in y
    }

    private data class HorizontalEdge(val x: LongRange, val y: Long) : Edge {

        val intermediateX = (x.first + 1)..x.last

        constructor(tile1: Point, tile2: Point) : this(min(tile1.x, tile2.x)..<max(tile1.x, tile2.x), tile1.y)

        infix fun intersectsWith(verticalEdge: VerticalEdge): Boolean =
            y in verticalEdge.intermediateY && verticalEdge.x in intermediateX

        override fun contains(point: Point): Boolean = point.y == y && point.x in x
        infix fun isBelow(point: Point): Boolean = point.x in x && y > point.y
    }

    private fun Point.isWithinPolygon(edges: List<Edge>): Boolean {
        // If there is an even amount of horizontal edges below this point, we are outside the polygon
        return edges.any { this in it }
                || edges.count { it is HorizontalEdge && it isBelow this } % 2 == 1
    }
}
