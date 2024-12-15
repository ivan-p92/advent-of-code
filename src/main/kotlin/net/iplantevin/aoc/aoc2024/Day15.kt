package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.*

object Day15 {

    fun part1(input: String): Long {
        val warehouse = parse(input)
        moveRobot(warehouse)
        return gpsSum(warehouse.map)
    }

    fun part2(input: String): Long {
        val warehouse = widenMap(parse(input))
        moveRobot2(warehouse)
        return gpsSum(warehouse.map)
    }

    private fun moveRobot(warehouse: Warehouse) {
        var robot = warehouse.robot
        val map = warehouse.map
        map[robot] = '.'
        warehouse.moves.forEach { direction ->
            val nextPosition = robot.move(direction)
            when (map[nextPosition]) {
                '.' -> robot = nextPosition
                'O' -> {
                    firstFreeSpotInDirection(map, nextPosition, direction)?.let { freeSpot ->
                        robot = nextPosition
                        map[freeSpot] = 'O'
                        map[nextPosition] = '.'
                    }
                }
            }
        }
        map[robot] = '@'
    }

    private fun firstFreeSpotInDirection(map: MapGrid<Char>, start: Point, direction: Direction): Point? {
        val nextPosition = start.move(direction)
        return when (map[nextPosition]) {
            '.' -> nextPosition
            'O' -> firstFreeSpotInDirection(map, nextPosition, direction)
            else -> null
        }
    }

    private fun moveRobot2(warehouse: Warehouse) {
        var robot = warehouse.robot
        val map = warehouse.map
        warehouse.moves.forEach { direction ->
            val nextPosition = robot.move(direction)
            when (map[nextPosition]) {
                '.' -> {
                    map[robot] = '.'
                    robot = nextPosition
                    map[robot] = '@'
                }

                '[', ']' -> {
                    findMoveableBoxes(map, robot, direction)?.let { moveableBoxes ->
                        robot = nextPosition
                        val movedBoxes = moveableBoxes.map { box -> box.move(direction) to map[box]!! }
                        moveableBoxes.forEach { box -> map[box] = '.' }
                        map += movedBoxes
                        map[robot] = '@'
                    }
                }
            }
        }
    }

    private fun findMoveableBoxes(map: MapGrid<Char>, start: Point, direction: Direction): List<Point>? {
        val nextPosition = start.move(direction)
        val nextObject = map[nextPosition]
        return when {
            nextObject == '.' -> listOf(start)
            (nextObject == '[' || nextObject == ']') && direction.isHorizontal() -> {
                findMoveableBoxes(map, nextPosition, direction)?.let {
                    it + start
                }
            }

            nextObject == '[' && direction.isVertical() -> {
                val left = findMoveableBoxes(map, nextPosition, direction)
                val right = findMoveableBoxes(map, nextPosition + Point(1, 0), direction)
                if (left == null || right == null) {
                    null
                } else {
                    left + right + start
                }
            }

            nextObject == ']' && direction.isVertical() -> {
                val right = findMoveableBoxes(map, nextPosition, direction)
                val left = findMoveableBoxes(map, nextPosition + Point(-1, 0), direction)
                if (left == null || right == null) {
                    null
                } else {
                    left + right + start
                }
            }

            else -> null
        }
    }

    private fun gpsSum(map: MapGrid<Char>): Long = map.entries.sumOf { (point, c) ->
        if (c == 'O' || c == '[') 100 * point.y + point.x else 0
    }

    private fun parse(input: String): Warehouse {
        val (map, moves) = input.split("\n\n")
        val dimensions = map.dimensions()
        var robot = Point(0, 0)
        val mapGrid = map.toMapGrid { point, char ->
            if (char == '@') robot = point
            char
        }
        val directions = moves.mapNotNull { Direction.ofArrow(it) }
        return Warehouse(mapGrid, Point(dimensions.first, dimensions.second), robot, directions)
    }

    private fun widenMap(warehouse: Warehouse): Warehouse {
        val wideMap = mutableMapOf<Point, Char>()
        var robot = Point(0, 0)
        repeat(warehouse.dimensions.yInt) { y ->
            repeat(warehouse.dimensions.xInt) { x ->
                val wideX = 2 * x
                val char = warehouse.map[Point(x, y)]
                when (char) {
                    '.' -> {
                        wideMap[Point(wideX, y)] = '.'
                        wideMap[Point(wideX + 1, y)] = '.'
                    }

                    'O' -> {
                        wideMap[Point(wideX, y)] = '['
                        wideMap[Point(wideX + 1, y)] = ']'
                    }

                    '#' -> {
                        wideMap[Point(wideX, y)] = '#'
                        wideMap[Point(wideX + 1, y)] = '#'
                    }

                    '@' -> {
                        robot = Point(wideX, y)
                        wideMap[robot] = '@'
                        wideMap[robot + Point(1, 0)] = '.'
                    }
                }
            }
        }
        val dimensions = Point(warehouse.dimensions.x * 2, warehouse.dimensions.y)
        return Warehouse(wideMap, dimensions, robot, warehouse.moves)
    }

    private data class Warehouse(
        val map: MapGrid<Char>,
        val dimensions: Point,
        val robot: Point,
        val moves: List<Direction>
    ) {
        fun printMap() {
            repeat(dimensions.yInt) { y ->
                repeat(dimensions.xInt) { x ->
                    print(map[Point(x, y)])
                }
                println()
            }
        }
    }
}
