package net.iplantevin.aoc.aoc2025

object Day3 {

    fun part1(input: String): Long = parse(input).sumOf { batteries ->
        solve(2, batteries).joltage()
    }

    fun part2(input: String): Long = parse(input).sumOf { batteries ->
        solve(12, batteries).joltage()
    }

    private fun parse(input: String): List<List<Int>> = input.lines().map { line ->
        line.map { it.digitToInt() }
    }

    private fun solve(remainingLength: Int, batteries: List<Int>): List<Int> {
        if (remainingLength == 1) {
            return listOf(batteries.max())
        }
        val max = batteries.dropLast(remainingLength - 1).withIndex().maxBy { it.value }
        val tail = batteries.drop(max.index + 1)
        return listOf(max.value) + solve(remainingLength - 1, tail)
    }

    private fun List<Int>.joltage(): Long = joinToString(separator = "").toLong()
}
