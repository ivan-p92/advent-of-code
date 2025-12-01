package net.iplantevin.aoc.aoc2025

import io.kotest.matchers.shouldBe

import net.iplantevin.aoc.util.AdventOfCodeTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class AdventOfCode2025Test : AdventOfCodeTest(2025) {

    @Test
    fun `day 1 - part 1`() {
        Day1.part1(
            """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
            """.trimIndent()
        ) shouldBe 3

        timing { Day1.part1(input(1)) shouldBe 1102 }
    }

    @Test
    fun `day 1 - part 2`() {
        Day1.part2(
            """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
            """.trimIndent()
        ) shouldBe 6

        timing { Day1.part2(input(1)) shouldBe 6175 }
    }

    @Disabled
    @Test
    fun `day 2 - part 1`() {
        Day2.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day2.part1(input(2)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 2 - part 2`() {
        Day2.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day2.part2(input(2)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 3 - part 1`() {
        Day3.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day3.part1(input(3)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 3 - part 2`() {
        Day3.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day3.part2(input(3)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 4 - part 1`() {
        Day4.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day4.part1(input(4)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 4 - part 2`() {
        Day4.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day4.part2(input(4)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 5 - part 1`() {
        Day5.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day5.part1(input(5)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 5 - part 2`() {
        Day5.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day5.part2(input(5)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 6 - part 1`() {
        Day6.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day6.part1(input(6)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 6 - part 2`() {
        Day6.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day6.part2(input(6)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 7 - part 1`() {
        Day7.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day7.part1(input(7)) shouldBe 0L }
    }

    @Disabled
    @Test
    fun `day 7 - part 2`() {
        Day7.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day7.part2(input(7)) shouldBe 0L }
    }

    @Disabled
    @Test
    fun `day 8 - part 1`() {
        Day8.part1(
            """""".trimIndent()
        ) shouldBe 0

        Day8.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day8.part1(input(8)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 8 - part 2`() {
        Day8.part2(
            """""".trimIndent()
        ) shouldBe 0

        Day8.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day8.part2(input(8)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 9 - part 1`() {
        Day9.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day9.part1(input(9)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 9 - part 2`() {
        Day9.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day9.part2(input(9)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 10 - part 1`() {
        Day10.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day10.part1(input(10)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 10 - part 2`() {
        Day10.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day10.part2(input(10)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 11 - part 1`() {
        Day11.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day11.part1(input(11)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 11 - part 2`() {
        Day11.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day11.part2(input(11)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 12 - part 1`() {
        Day12.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day12.part1(input(12)) shouldBe 0L }
    }

    @Disabled
    @Test
    fun `day 12 - part 2`() {
        Day12.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day12.part2(input(12)) shouldBe 0 }
    }
}
