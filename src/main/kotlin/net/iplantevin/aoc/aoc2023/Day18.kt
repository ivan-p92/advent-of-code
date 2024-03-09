package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.Point
import kotlin.math.abs

object Day18 {

    fun problem18a(input: String): Long {
        val instructions = parseInstructions(input)
        val trench = trenchVertices(instructions)
        return calculateArea(trench)
    }

    fun problem18b(input: String): Long {
        val instructions = parseInstructions(input)
        val trench = trenchVertices(fixInstructions(instructions))
        return calculateArea(trench)
    }

    private fun parseInstructions(input: String): List<DigInstruction> {
        val regex = """(\w) (\d+) \(#(.{6})\)""".toRegex()
        val directionMapping = mapOf(
            "U" to NORTH,
            "D" to SOUTH,
            "R" to EAST,
            "L" to WEST,
        )
        return input.lines().map { line ->
            val (_, direction, steps, color) = regex.matchEntire(line)!!.groupValues
            DigInstruction(
                direction = directionMapping[direction]!!,
                steps = steps.toInt(),
                edgeColor = color,
            )
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun fixInstructions(instructions: List<DigInstruction>): List<DigInstruction> {
        return instructions.map { (_, _, color) ->
            val steps = color.dropLast(1).hexToInt()
            val direction = when (color.last()) {
                '0' -> EAST
                '1' -> SOUTH
                '2' -> WEST
                else -> NORTH
            }
            DigInstruction(direction, steps, "")
        }
    }

    private fun trenchVertices(instructions: List<DigInstruction>): List<Point> {
        var currentVertex = Point(0, 0)
        val vertices = mutableListOf(currentVertex)
        for (instruction in instructions) {
            currentVertex += instruction.direction.delta * instruction.steps
            vertices += currentVertex
        }
        return vertices
    }

    private fun calculateArea(trenchVertices: List<Point>): Long {
        // We need to calculate the area and the perimeter to find the total
        // points that are dug out.
        // See https://en.wikipedia.org/wiki/Polygon#Area
        var areaSum = 0L
        var perimeter = 0L
        trenchVertices.forEachIndexed { i: Int, point: Point ->
            val nextPoint = trenchVertices.getOrElse(i + 1) { trenchVertices.first() }
            areaSum += point.x * nextPoint.y - nextPoint.x * point.y
            perimeter += point.distance(nextPoint)
        }
        val area = (0.5 * abs(areaSum)).toLong()
        return area + perimeter / 2 + 1
    }

    private data class DigInstruction(
        val direction: Direction,
        val steps: Int,
        val edgeColor: String,
    )
}
