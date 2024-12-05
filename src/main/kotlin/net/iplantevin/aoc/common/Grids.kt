package net.iplantevin.aoc.common

import kotlin.math.abs

typealias Grid<T> = List<List<T>>

data class Point(val x: Long, val y: Long) {

    val xInt
        get() = x.toInt()

    val yInt
        get() = y.toInt()

    constructor(x: Int, y: Int) : this(x.toLong(), y.toLong())

    fun neighbours(): List<Point> {
        val result = mutableListOf<Point>()
        (-1..1).forEach { yOffset ->
            (-1..1).forEach { xOffset ->
                if (xOffset == 0 && yOffset == 0) {
                    // skip
                } else {
                    result += Point(x + xOffset, y + yOffset)
                }
            }
        }
        return result
    }

    fun move(direction: Direction): Point = this + direction.delta

    fun distance(other: Point): Long = abs(x - other.x) + abs(y - other.y)

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    operator fun times(factor: Int): Point = Point(x * factor, y * factor)
}

enum class Direction(val delta: Point) {
    EAST(Point(1, 0)),
    NORTH(Point(0, -1)),
    WEST(Point(-1, 0)),
    SOUTH(Point(0, 1));

    fun turnLeft(): Direction {
        return entries[(this.ordinal + 1) % entries.size]
    }

    fun turnRight(): Direction {
        return entries[(entries.size + (this.ordinal - 1)) % entries.size]
    }
}

fun <T> Grid<T>.printToString(): String {
    return joinToString("\n") {
        it.joinToString("")
    }
}
