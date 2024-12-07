package net.iplantevin.aoc.aoc2024

object Day3 {

    fun part1(input: String): Int {
        return extractMultiplications(input).fold(0) { sum, (a, b) -> sum + a * b }
    }

    fun part2(input: String): Int {
        return extractEnabledMultiplications(input).fold(0) { sum, (a, b) -> sum + a * b }
    }

    private fun extractMultiplications(input: String): Sequence<Pair<Int, Int>> {
        val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
        return mulRegex.findAll(input).map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
    }

    private fun extractEnabledMultiplications(input: String): Sequence<Pair<Int, Int>> {
        val mulRegex = """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".toRegex()
        var enabled = true
        return mulRegex.findAll(input).mapNotNull { match ->
            when (match.groupValues.first()) {
                "do()" -> {
                    enabled = true
                    null
                }

                "don't()" -> {
                    enabled = false
                    null
                }

                else -> if (enabled) match.groupValues[2].toInt() to match.groupValues[3].toInt() else null
            }
        }
    }
}
