package net.iplantevin.aoc.aoc2024

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2024.Day1.problem1a
import net.iplantevin.aoc.aoc2024.Day1.problem1b
import net.iplantevin.aoc.aoc2024.Day2.problem2a
import net.iplantevin.aoc.aoc2024.Day2.problem2b
import net.iplantevin.aoc.aoc2024.Day3.problem3a
import net.iplantevin.aoc.aoc2024.Day3.problem3b
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

    @Test
    fun part2a() {
        problem2a(
            """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
        ) shouldBe 2

        timing { problem2a(input(24, 2)) shouldBe 663 }
    }

    @Test
    fun part2b() {
        problem2b(
            """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
        ) shouldBe 4

        timing { problem2b(input(24, 2)) shouldBe 692 }
    }

    @Test
    fun part3a() {
        problem3a(
            """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
            """.trimIndent()
        ) shouldBe 161

        timing { problem3a(input(24, 3)) shouldBe 188741603 }
    }

    @Test
    fun part3b() {
        problem3b(
            """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """.trimIndent()
        ) shouldBe 48

        timing { problem3b(input(24, 3)) shouldBe 67269798 }
    }
}
