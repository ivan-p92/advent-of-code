package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.MapGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toMapGrid

object Day12 {

    fun part1(input: String): Int {
        val plots = input.toMapGrid { char -> char }
        return detectRegions(plots).sumOf { region ->
            val area = region.plots.size
            val perimeter = region.plots.sumOf { 4 - adjacentPlotsWithSamePlant(it, region.plant, plots).size }
            val fencingCost = area * perimeter
//            println("$plant: area $area x perimeter $perimeter = $fencingCost")
            fencingCost
        }
    }

    fun part2(input: String): Int {
        val plots = input.toMapGrid { char -> char }
        return detectRegions(plots).sumOf { region ->
            val area = region.plots.size
            val sides = countSides(region, plots)
            val fencingCost = area * sides
//            println("${region.plant}: area $area x sides $sides = $fencingCost")
            fencingCost
        }
    }

    private fun detectRegions(plots: MapGrid<Char>): List<Region> {
        val visitedPlots = mutableSetOf<Point>()
        val regions = mutableListOf<List<Point>>()
        for ((plot, plant) in plots) {
            if (plot !in visitedPlots)
                regions += detectRegion(plot, plant, visitedPlots, plots, mutableListOf())
        }
        return regions.map { Region(it, plots[it.first()]!!) }
    }

    private fun detectRegion(
        plot: Point,
        plant: Char,
        visitedPlots: MutableSet<Point>,
        plots: MapGrid<Char>,
        region: MutableList<Point>
    ): List<Point> {
        visitedPlots += plot
        region += plot
        adjacentPlotsWithSamePlant(plot, plant, plots).forEach { neighbour ->
            if (neighbour !in visitedPlots) detectRegion(neighbour, plant, visitedPlots, plots, region)
        }
        return region
    }

    private fun adjacentPlotsWithSamePlant(plot: Point, plant: Char, plots: MapGrid<Char>): List<Point> =
        Direction.entries.mapNotNull { direction ->
            val neighbour = plot.move(direction)
            if (plots[neighbour] == plant) neighbour else null
        }

    private fun countSides(region: Region, plots: MapGrid<Char>): Int {
        val fences = mutableSetOf<Fence>()

        Direction.entries.forEach { fenceDirection ->
            region.plots.forEach { plot ->
                if (plots[plot.move(fenceDirection)] != region.plant) {
                    fences += Fence(plot, fenceDirection)
                }
            }
        }
        return fences.count { (plot, direction) ->
            Fence(plot.move(direction.turnRight()), direction) !in fences
        }
    }

    private data class Region(val plots: List<Point>, val plant: Char)
    private data class Fence(val plot: Point, val direction: Direction)
}
