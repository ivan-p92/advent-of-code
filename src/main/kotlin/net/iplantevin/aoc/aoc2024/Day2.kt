package net.iplantevin.aoc.aoc2024

import kotlin.math.abs

object Day2 {

    fun part1(input: String): Int = parse(input).count { reportIsSafe(it) }

    fun part2(input: String): Int = parse(input).count { reportIsSafe(it, lenient = true) }

    private fun parse(input: String) = input.lineSequence().map { it.split(" ").map(String::toInt) }

    private fun reportIsSafe(report: List<Int>, lenient: Boolean = false): Boolean {
        val isIncreasing = report[1] > report[0]

        for (i in 0..<(report.size - 1)) {
            val a = report[i]
            val b = report[i + 1]
            val isSafe = levelStepIsSafe(a, b, isIncreasing)
            when {
                !isSafe && lenient -> {
                    val withoutPreviousIsSafe =
                        if (i > 0) reportIsSafe(report.toMutableList().apply { removeAt(i - 1) }) else false
                    return withoutPreviousIsSafe || reportIsSafe(report.toMutableList().apply { removeAt(i) }) ||
                            reportIsSafe(report.toMutableList().apply { removeAt(i + 1) })
                }

                !isSafe -> return false
            }
        }
        return true
    }

    private fun levelStepIsSafe(a: Int, b: Int, increasing: Boolean): Boolean = when {
        a == b -> false
        b > a && !increasing || b < a && increasing -> false
        abs(a - b) > 3 -> false
        else -> true
    }
}
