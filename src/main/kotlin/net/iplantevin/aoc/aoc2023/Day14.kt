package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.ArrayGrid
import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.*
import net.iplantevin.aoc.common.printToString
import net.iplantevin.aoc.common.toCharArrayGrid

object Day14 {

    fun problem14a(input: String): Int {
        val lines = input.lines()
        var load = 0
        for (x in 0 until lines.first().length) {
            var emptySpots = 0
            for (y in lines.indices) {
                when (lines[y][x]) {
                    'O' -> load += lines.size - y + emptySpots
                    '.' -> emptySpots++
                    '#' -> emptySpots = 0
                }
            }
        }
        return load
    }

    fun problem14b(input: String): Int {
        var grid = input.toCharArrayGrid()
        val stateHistory = LinkedHashSet<ArrayGrid<Char>>()
        for (cycle in 1..1_000_000_000) {
            grid = tilt(grid, NORTH)
//            println("After north:\n${grid.printToString()}")
            grid = tilt(grid, WEST)
//            println("After west\n${grid.printToString()}")
            grid = tilt(grid, SOUTH)
//            println("After south:\n${grid.printToString()}")
            grid = tilt(grid, EAST)
//            println("After $cycle cycles:\n${grid.printToString()}\n")
            if (grid in stateHistory) {
                val firstOccurrence = stateHistory.indexOf(grid) + 1
                println("Reached identical state again after ${cycle - firstOccurrence} cycles:\n${grid.printToString()}\n")
                val finalStateIndex =
                    ((1_000_000_000 - firstOccurrence) % (cycle - firstOccurrence)) + (firstOccurrence - 1)
                val finalState = stateHistory.toList()[finalStateIndex]
                println("Final state:\n${finalState.printToString()}")
                return loadOnNorth(finalState)
            } else {
                stateHistory += grid
            }
        }
        return -1
    }

    // Could have created a rotation function and applied a simpler tilt function on it.
    private fun tilt(grid: ArrayGrid<Char>, direction: Direction): MutableList<MutableList<Char>> {
        val newGrid = MutableList(grid.size) { y -> MutableList(grid.first().size) { x -> grid[y][x] } }
        if (direction == NORTH || direction == SOUTH) {
            for (x in 0 until grid.first().size) {
                val range = if (direction == NORTH) grid.indices else grid.indices.reversed()
                var emptySpots = 0
                for (y in range) {
                    when (grid[y][x]) {
                        'O' -> {
                            val newY = y + emptySpots * direction.delta.yInt
                            if (newY != y) {
                                newGrid[newY][x] = 'O'
                                newGrid[y][x] = '.'
                            }
                        }

                        '.' -> emptySpots++
                        '#' -> emptySpots = 0
                    }
                }
            }
        } else {
            for (y in 0 until grid.size) {
                val range = if (direction == WEST) grid.first().indices else grid.first().indices.reversed()
                var emptySpots = 0
                for (x in range) {
                    when (grid[y][x]) {
                        'O' -> {
                            val newX = x + emptySpots * direction.delta.xInt
                            if (newX != x) {
                                newGrid[y][newX] = 'O'
                                newGrid[y][x] = '.'
                            }
                        }

                        '.' -> emptySpots++
                        '#' -> emptySpots = 0
                    }
                }
            }
        }
        return newGrid
    }

    private fun loadOnNorth(grid: ArrayGrid<Char>): Int = grid.mapIndexed { y, chars ->
        chars.count { it == 'O' } * (grid.size - y)
    }.sum()
}
