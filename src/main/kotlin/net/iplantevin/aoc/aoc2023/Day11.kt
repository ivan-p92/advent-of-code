package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Point

object Day11 {

    fun problem11(universe: String, expansionFactor: Int): Long {
        val (galaxies, emptyRows, emptyColumns) = inspect(universe)
        val expandedGalaxies = expand(expansionFactor, galaxies, emptyRows, emptyColumns)
        val cartesianProduct = mutableSetOf<Pair<Point, Point>>()
        expandedGalaxies.map { galaxy ->
            expandedGalaxies.map { otherGalaxy ->
                if (galaxy != otherGalaxy && Pair(otherGalaxy, galaxy) !in cartesianProduct) {
                    cartesianProduct += Pair(galaxy, otherGalaxy)
                }
            }
        }
        return cartesianProduct.sumOf { (g1, g2) -> g1.distance(g2) }
    }

    private fun inspect(universe: String): Triple<List<Point>, List<Int>, List<Int>> {
        val galaxies = mutableListOf<Point>()
        val rows = universe.lines()
        val emptyRows = mutableListOf<Int>()
        rows.forEachIndexed { y, row ->
            var allEmpty = true
            row.forEachIndexed { x, c ->
                if (c != '.') allEmpty = false
                if (c == '#') galaxies += Point(x, y)
            }
            if (allEmpty) emptyRows += y
        }
        val width = rows.first().length
        val height = rows.size
        val emptyColumns = mutableListOf<Int>()
        for (x in 0 until width) {
            var allEmpty = true
            for (y in 0 until height) {
                if (rows[y][x] != '.') {
                    allEmpty = false
                    break
                }
            }
            if (allEmpty) emptyColumns += x
        }
        return Triple(galaxies, emptyRows, emptyColumns)
    }

    private fun expand(factor: Int, galaxies: List<Point>, emptyRows: List<Int>, emptyColumns: List<Int>): List<Point> {
        return galaxies.map { galaxy ->
            val yExpansion = emptyRows.indexOfLast { it < galaxy.y }.let { if (it == -1) 0 else it + 1 }
            val xExpansion = emptyColumns.indexOfLast { it < galaxy.x }.let { if (it == -1) 0 else it + 1 }
            galaxy + Point(factor * xExpansion, factor * yExpansion)
        }
    }
}
