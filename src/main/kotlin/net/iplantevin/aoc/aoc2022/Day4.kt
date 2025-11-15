package net.iplantevin.aoc.aoc2022

import net.iplantevin.aoc.common.size

object Day4 {

    private val pattern = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

    fun part1(input: String): Int = countIntersects(input) { intersectSize, range1, range2 ->
        intersectSize == range1.size() || intersectSize == range2.size()
    }

    fun part2(input: String): Int = countIntersects(input) { intersectSize, _, _ ->
        intersectSize > 0
    }

    private fun countIntersects(
        input: String,
        predicate: (intersectSize: Int, range1: IntRange, range2: IntRange) -> Boolean
    ): Int {
        return input.lines().count { line ->
            val (a, b, c, d) = pattern.matchEntire(line)!!.groupValues.drop(1)
            val range1 = IntRange(a.toInt(), b.toInt())
            val range2 = IntRange(c.toInt(), d.toInt())
            val intersectSize = range1.intersect(range2).size
            println("$line: $intersectSize")
            predicate(intersectSize, range1, range2)
        }
    }
}
