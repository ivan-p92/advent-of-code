package net.iplantevin.aoc.aoc2022

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.util.AdventOfCodeTest
import org.junit.jupiter.api.Test

class AdventOfCode2022Test : AdventOfCodeTest(2022) {

    @Test
    fun `day 1 - part 1`() {
        Day1.part1(
            """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()
        ) shouldBe 24000

        timing { Day1.part1(input(1)) shouldBe 70720 }
    }

    @Test
    fun `day 1 - part 2`() {
        Day1.part2(
            """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()
        ) shouldBe 45000

        timing { Day1.part2(input(1)) shouldBe 207148 }
    }

    @Test
    fun `day 2 - part 1`() {
        Day2.part1(
            """
            A Y
            B X
            C Z
            """.trimIndent()
        ) shouldBe 15

        timing { Day2.part1(input(2)) shouldBe 12645 }
    }

    @Test
    fun `day 2 - part 2`() {
        Day2.part2(
            """
            A Y
            B X
            C Z
            """.trimIndent()
        ) shouldBe 12

        timing { Day2.part2(input(2)) shouldBe 11756 }
    }
    @Test
    fun `day 3 - part 1`() {
        Day3.part1(
            """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """.trimIndent()
        ) shouldBe 157

        timing { Day3.part1(input(3)) shouldBe 8072 }
    }

    @Test
    fun `day 3 - part 2`() {
        Day3.part2(
            """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """.trimIndent()
        ) shouldBe 70

        timing { Day3.part2(input(3)) shouldBe 2567 }
    }
}
