package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid

object Day4 {

    fun part1(input: String): Int {
        val grid = input.toMapGrid { it }
        return grid.count { (point, char) ->
            char == '@' && countAdjacentRolls(grid, point) < 4
        }
    }

    fun part2(input: String): Int {
        val grid = input.toMapGrid { it }
        var totalRemovedRolls = 0
        do {
            val removedRolls = removeAccessibleRolls(grid)
            totalRemovedRolls += removedRolls
        } while (removedRolls > 0)
        return totalRemovedRolls
    }

    private fun countAdjacentRolls(grid: MapGrid<Char>, point: Point): Int =
        point.neighbours().count { grid[it] == '@' }

    private fun removeAccessibleRolls(grid: MapGrid<Char>): Int {
        var removedRolls = 0
        grid.entries.forEach { (point, char) ->
            if (char == '@' && countAdjacentRolls(grid, point) < 4) {
                grid[point] = '.'
                removedRolls += 1
            }
        }
        return removedRolls
    }
}
