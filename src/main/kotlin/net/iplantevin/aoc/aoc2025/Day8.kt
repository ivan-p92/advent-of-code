package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.Point3D
import net.iplantevin.aoc.common.distinctPairs
import net.iplantevin.aoc.common.product

object Day8 {

    fun part1(input: String, connections: Int): Int {
        val junctionBoxes = parse(input)
        val circuits = mutableMapOf<Point3D, MutableSet<Point3D>>()
        junctionBoxes.distinctPairs()
            .sortedBy { (box1, box2) -> box1 distanceTo box2 }
            .take(connections)
            .forEach { (box1, box2) ->
                val circuit1 = circuits[box1] ?: mutableSetOf(box1)
                val circuit2 = circuits[box2] ?: mutableSetOf(box2)
                circuit1.addAll(circuit2)
                circuit1.forEach { box ->
                    circuits[box] = circuit1
                }
            }
        return circuits.values.toSet().map { it.size }.sortedDescending().take(3).product()
    }

    fun part2(input: String): Long {
        val junctionBoxes = parse(input)
        val circuits = mutableMapOf<Point3D, MutableSet<Point3D>>()
        junctionBoxes.distinctPairs()
            .sortedBy { (box1, box2) -> box1 distanceTo box2 }
            .forEach { (box1, box2) ->
                val circuit1 = circuits[box1] ?: mutableSetOf(box1)
                val circuit2 = circuits[box2] ?: mutableSetOf(box2)
                circuit1.addAll(circuit2)
                if (circuit1.size == junctionBoxes.size) {
                    return box1.x * box2.x
                }
                circuit1.forEach { box ->
                    circuits[box] = circuit1
                }
            }
        return -1
    }

    private fun parse(input: String): List<Point3D> = input.lines().map { line ->
        val (x, y, z) = line.split(",")
        Point3D(x.toLong(), y.toLong(), z.toLong())
    }
}
