package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.distinctPairs
import net.iplantevin.aoc.common.toMapGrid

object Day8 {

    fun part1(input: String): Int = solve(input, equidistant = true)

    fun part2(input: String): Int = solve(input, equidistant = false)

    private fun solve(input: String, equidistant: Boolean): Int {
        val grid = input.toMapGrid { char -> char }
        val antennaeByFrequency = grid.entries.filter { it.value != '.' }.groupBy({ it.value }) { it.key }
        return antennaeByFrequency.values.flatMapTo(mutableSetOf()) { antennae ->
            detectAntinodes(antennae, grid, equidistant)
        }.size
    }

    private fun detectAntinodes(antennae: List<Point>, grid: MapGrid<Char>, equidistant: Boolean): List<Point> {
        val pairs = antennae.distinctPairs()
        val antinodes = mutableListOf<Point>()
        if (!equidistant && antennae.size >= 2) antinodes += antennae

        pairs.forEach { (a, b) ->
            val diff = b - a
            var antinode = b
            do {
                antinode += diff
                if (antinode in grid) antinodes += antinode
            } while (!equidistant && antinode in grid)
            antinode = a
            do {
                antinode -= diff
                if (antinode in grid) antinodes += antinode
            } while (!equidistant && antinode in grid)
        }
        return antinodes
    }
}
