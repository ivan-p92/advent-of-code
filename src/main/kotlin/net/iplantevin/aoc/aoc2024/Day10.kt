package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid
import java.util.*

object Day10 {

    fun part1(input: String): Int {
        val (trailHeads, map) = parseTopographicMap(input)
        return trailHeads.sumOf { trailHead -> score(trailHead, map) }
    }

    fun part2(input: String): Int {
        val (trailHeads, map) = parseTopographicMap(input)
        return trailHeads.sumOf { trailHead -> countDistinctHikingTrails(trailHead, map) }
    }

    private fun parseTopographicMap(input: String): Pair<List<Point>, MapGrid<Int>> {
        val trailHeads = mutableListOf<Point>()
        val map = input.toMapGrid { point, char ->
            val digit = char.digitToIntOrNull() ?: -1
            if (digit == 0) trailHeads += point
            digit
        }
        return trailHeads to map
    }

    private fun score(trailHead: Point, map: MapGrid<Int>): Int {
        val positionsToWalk = LinkedList<Point>()
        positionsToWalk.push(trailHead)
        val visitedPositions = mutableSetOf<Point>()
        var numberOfReachableTops = 0
        while (positionsToWalk.isNotEmpty()) {
            val position = positionsToWalk.pop()
            visitedPositions += position
            if (map[position]!! == 9) numberOfReachableTops++
            Direction.entries.forEach { direction ->
                val nextPosition = position + direction.delta
                if (nextPosition in map && map[nextPosition] == map[position]!! + 1 && nextPosition !in visitedPositions) {
                    positionsToWalk.push(nextPosition)
                }
            }
        }
        return numberOfReachableTops
    }

    private fun countDistinctHikingTrails(position: Point, map: MapGrid<Int>): Int {
        return if (map[position] == 9) {
            // Yay, we've reached a top so have (at least) 1 valid trail!
            1
        } else {
            Direction.entries.sumOf { direction ->
                val nextPosition = position + direction.delta
                if (nextPosition in map && map[nextPosition] == map[position]!! + 1) {
                    countDistinctHikingTrails(nextPosition, map)
                } else 0
            }
        }
    }
}
