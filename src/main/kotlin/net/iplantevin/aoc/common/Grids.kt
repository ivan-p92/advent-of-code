package net.iplantevin.aoc.common

data class Point(val x: Int, val y: Int) {

    fun adjacentPoints(): List<Point> {
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

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}

enum class Direction(val delta: Point) {
    EAST(Point(1, 0)),
    WEST(Point(-1, 0)),
    NORTH(Point(0, -1)),
    SOUTH(Point(0, 1));
}
