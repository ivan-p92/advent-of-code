package net.iplantevin.aoc.common

import kotlin.math.abs

typealias ArrayGrid<T> = List<List<T>>

typealias MapGrid<T> = MutableMap<Point, T>

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

    fun adjacentPoints(): List<Point> = listOf(
        Point(x + 1, y),
        Point(x - 1, y),
        Point(x, y + 1),
        Point(x, y - 1)
    )

    fun move(direction: Direction): Point = this + direction.delta

    fun distance(other: Point): Long = abs(x - other.x) + abs(y - other.y)

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)

    operator fun times(factor: Int): Point = Point(x * factor, y * factor)

    operator fun times(factor: Long): Point = Point(x * factor, y * factor)

    operator fun contains(other: Point): Boolean = other.x >= 0 && other.y >= 0 && other.x <= x && other.y <= y
}

enum class Direction(val delta: Point, val arrow: Char) {
    EAST(Point(1, 0), '>'),
    NORTH(Point(0, -1), '^'),
    WEST(Point(-1, 0), '<'),
    SOUTH(Point(0, 1), 'v');

    fun turnLeft(): Direction {
        return entries[(this.ordinal + 1) % entries.size]
    }

    fun turnRight(): Direction {
        return entries[(entries.size + (this.ordinal - 1)) % entries.size]
    }

    fun isHorizontal(): Boolean = this == WEST || this == EAST

    fun isVertical(): Boolean = this == SOUTH || this == NORTH

    companion object {
        fun ofArrow(char: Char): Direction? = when (char) {
            '^' -> NORTH
            '>' -> EAST
            '<' -> WEST
            'v' -> SOUTH
            else -> null
        }
    }
}

data class Move(val position: Point, val direction: Direction, val stepSize: Int = 1) {

    fun nextMove(): Move = copy(position = (direction.delta * stepSize) + position)

    fun turnLeft(): Move = copy(direction = direction.turnLeft())

    fun turnRight(): Move = copy(direction = direction.turnRight())
}

fun <T> ArrayGrid<T>.printToString(): String {
    return joinToString("\n") {
        it.joinToString("")
    }
}

fun String.toCharArrayGrid(): ArrayGrid<Char> = lines().map { it.toList() }

fun <T> String.toMapGrid(transform: (char: Char) -> T): MapGrid<T> = toMapGrid { _, char -> transform(char) }

fun <T> String.toMapGrid(transform: (Point, Char) -> T?): MapGrid<T> {
    val grid = mutableMapOf<Point, T>()
    lines().forEachIndexed { y: Int, line: String ->
        line.forEachIndexed { x, char ->
            val point = Point(x, y)
            transform(point, char)?.let { grid[point] = it }
        }
    }
    return grid
}

fun String.dimensions(): Pair<Int, Int> = lines().let { it.first().length to it.size }
