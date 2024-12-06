package net.iplantevin.aoc.common

import kotlin.math.abs

typealias ArrayGrid<T> = List<List<T>>

typealias MapGrid<T> = Map<Point, T>

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

fun <T> String.toMapGrid(transform: (char: Char) -> T): MapGrid<T> {
    val grid = mutableMapOf<Point, T>()
    lines().forEachIndexed { y: Int, line: String ->
        line.forEachIndexed { x, char ->
            grid[Point(x, y)] = transform(char)
        }
    }
    return grid
}

fun <T> String.toMapGrid(transform: (Point, Char) -> T): MapGrid<T> {
    val grid = mutableMapOf<Point, T>()
    lines().forEachIndexed { y: Int, line: String ->
        line.forEachIndexed { x, char ->
            val point = Point(x, y)
            grid[point] = transform(point, char)
        }
    }
    return grid
}

fun String.dimensions(): Pair<Int, Int> = lines().let { it.first().length to it.size }
