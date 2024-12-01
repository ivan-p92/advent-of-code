package net.iplantevin.aoc.aoc2024

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2024.Day1.problem1a
import net.iplantevin.aoc.aoc2024.Day1.problem1b
import net.iplantevin.aoc.util.input
import net.iplantevin.aoc.util.timing
import org.junit.jupiter.api.Test


class AdventOfCode2024Test {

    @Test
    fun part1a() {
        problem1a(
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        ) shouldBe 11L

        timing { problem1a(input(24, 1)) shouldBe 2086478L }
    }

    @Test
    fun part1b() {
        problem1b(
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        ) shouldBe 31L

        timing { problem1b(input(24, 1)) shouldBe 24941624L }
    }
}
