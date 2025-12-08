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

    @Test
    fun `day 2 - part 1`() {
        Day2.part1(
            """11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124""".trimIndent()
        ) shouldBe 1227775554

        timing { Day2.part1(input(2)) shouldBe 21898734247 }
    }

    @Test
    fun `day 2 - part 2`() {
        Day2.part2(
            """11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124""".trimIndent()
        ) shouldBe 4174379265

        timing { Day2.part2(input(2)) shouldBe 28915664389 }
    }

    @Test
    fun `day 3 - part 1`() {
        Day3.part1(
            """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
            """.trimIndent()
        ) shouldBe 357

        timing { Day3.part1(input(3)) shouldBe 17430 }
    }

    @Test
    fun `day 3 - part 2`() {
        Day3.part2(
            """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
            """.trimIndent()
        ) shouldBe 3121910778619

        timing { Day3.part2(input(3)) shouldBe 171975854269367 }
    }

    @Test
    fun `day 4 - part 1`() {
        Day4.part1(
            """
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
            """.trimIndent()
        ) shouldBe 13

        timing { Day4.part1(input(4)) shouldBe 1445 }
    }

    @Test
    fun `day 4 - part 2`() {
        Day4.part2(
            """
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
            """.trimIndent()
        ) shouldBe 43

        timing { Day4.part2(input(4)) shouldBe 8317 }
    }

    @Test
    fun `day 5 - part 1`() {
        Day5.part1(
            """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
            """.trimIndent()
        ) shouldBe 3

        timing { Day5.part1(input(5)) shouldBe 690 }
    }

    @Test
    fun `day 5 - part 2`() {
        Day5.part2(
            """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
            """.trimIndent()
        ) shouldBe 14

        timing { Day5.part2(input(5)) shouldBe 344323629240733 }
    }

    @Test
    fun `day 6 - part 1`() {
        Day6.part1(
            """
                123 328  51 64 
                 45 64  387 23 
                  6 98  215 314
                *   +   *   +
            """.trimIndent()
        ) shouldBe 4277556

        timing { Day6.part1(input(6)) shouldBe 3525371263915 }
    }

    @Test
    fun `day 6 - part 2`() {
        Day6.part2(
            """
                123 328  51 64 
                 45 64  387 23 
                  6 98  215 314
                *   +   *   + 
            """.trimIndent()
        ) shouldBe 3263827

        timing { Day6.part2(input(6)) shouldBe 6846480843636 }
    }

    @Test
    fun `day 7 - part 1`() {
        Day7.part1(
            """
                .......S.......
                ...............
                .......^.......
                ...............
                ......^.^......
                ...............
                .....^.^.^.....
                ...............
                ....^.^...^....
                ...............
                ...^.^...^.^...
                ...............
                ..^...^.....^..
                ...............
                .^.^.^.^.^...^.
                ...............
            """.trimIndent()
        ) shouldBe 21

        timing { Day7.part1(input(7)) shouldBe 1602 }
    }

    @Test
    fun `day 7 - part 2`() {
        Day7.part2(
            """
                .......S.......
                ...............
                .......^.......
                ...............
                ......^.^......
                ...............
                .....^.^.^.....
                ...............
                ....^.^...^....
                ...............
                ...^.^...^.^...
                ...............
                ..^...^.....^..
                ...............
                .^.^.^.^.^...^.
                ...............
            """.trimIndent()
        ) shouldBe 40

        timing { Day7.part2(input(7)) shouldBe 135656430050438 }
    }

    @Test
    fun `day 8 - part 1`() {
        Day8.part1(
            """
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
            """.trimIndent(),
            connections = 10
        ) shouldBe 40

        timing { Day8.part1(input(8), connections = 1000) shouldBe 68112 }
    }

    @Test
    fun `day 8 - part 2`() {
        Day8.part2(
            """
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
            """.trimIndent()
        ) shouldBe 25272

        timing { Day8.part2(input(8)) shouldBe 44543856 }
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
