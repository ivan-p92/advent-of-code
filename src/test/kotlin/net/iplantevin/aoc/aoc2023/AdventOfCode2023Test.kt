package net.iplantevin.aoc.aoc2023

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2023.Day1.problem1a
import net.iplantevin.aoc.aoc2023.Day1.problem1b
import net.iplantevin.aoc.aoc2023.Day10.problem10a
import net.iplantevin.aoc.aoc2023.Day2.problem2a
import net.iplantevin.aoc.aoc2023.Day2.problem2b
import net.iplantevin.aoc.aoc2023.Day3.problem3a
import net.iplantevin.aoc.aoc2023.Day3.problem3b
import net.iplantevin.aoc.aoc2023.Day4.problem4a
import net.iplantevin.aoc.aoc2023.Day4.problem4b
import net.iplantevin.aoc.aoc2023.Day5.problem5a
import net.iplantevin.aoc.aoc2023.Day5.problem5b
import net.iplantevin.aoc.aoc2023.Day6.problem6a
import net.iplantevin.aoc.aoc2023.Day6.problem6b
import net.iplantevin.aoc.aoc2023.Day7.problem7a
import net.iplantevin.aoc.aoc2023.Day7.problem7b
import net.iplantevin.aoc.aoc2023.Day8.problem8a
import net.iplantevin.aoc.aoc2023.Day8.problem8b
import net.iplantevin.aoc.aoc2023.Day9.problem9a
import net.iplantevin.aoc.aoc2023.Day9.problem9b
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.util.input
import net.iplantevin.aoc.util.timing
import org.junit.jupiter.api.Test

class AdventOfCode2023Test {

    @Test
    fun part1a() {
        problem1a(
            """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""
        ) shouldBe 142

        timing { problem1a(input(23, 1)) shouldBe 54877 }
    }

    @Test
    fun part1b() {
        problem1b("""two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen""") shouldBe 281

        timing { problem1b(input(23, 1)) shouldBe 54100 }
    }

    @Test
    fun part2a() {
        problem2a(
            """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""",
            12, 13, 14
        ) shouldBe 8

        timing { problem2a(input(23, 2), 12, 13, 14) shouldBe 2795 }
    }

    @Test
    fun part2b() {
        problem2b(
            """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"""
        ) shouldBe 2286

        timing { problem2b(input(23, 2)) shouldBe 75561 }
    }

    @Test
    fun part3a() {
        Point(0, 1).adjacentPoints() shouldBe listOf(
            Point(x = -1, y = 0),
            Point(x = 0, y = 0),
            Point(x = 1, y = 0),
            Point(x = -1, y = 1),
            Point(x = 1, y = 1),
            Point(x = -1, y = 2),
            Point(x = 0, y = 2),
            Point(x = 1, y = 2)
        )
        problem3a(
            """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""
        ) shouldBe 4361

        timing { problem3a(input(23, 3)) shouldBe 535351 }
    }

    @Test
    fun part3b() {
        problem3b(
            """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""
        ) shouldBe 467835

        timing { problem3b(input(23, 3)) shouldBe 87287096 }
    }

    @Test
    fun part4a() {
        problem4a(
            """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
        ) shouldBe 13

        timing { problem4a(input(23, 4)) shouldBe 25183 }
    }

    @Test
    fun part4b() {
        problem4b(
            """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
        ) shouldBe 30

        timing { problem4b(input(23, 4)) shouldBe 5667240 }
    }

    @Test
    fun part5a() {
        problem5a(
            """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
        ) shouldBe 35

        timing { problem5a(input(23, 5)) shouldBe 107430936L }
    }

    @Test
    fun part5b() {
        problem5b(
            """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
        ) shouldBe 46

        timing { problem5b(input(23, 5)) shouldBe 23738616L }
    }

    @Test
    fun part6a() {
        val finalInput = """
            Time:        41     77     70     96
            Distance:   249   1362   1127   1011
        """.trimIndent()

        problem6a(
            """Time:      7  15   30
Distance:  9  40  200"""
        ) shouldBe 288

        timing { problem6a(finalInput) shouldBe 771628 }
    }

    @Test
    fun part6b() {
        val finalInput = """
            Time:        41777096
            Distance:   249136211271011
        """.trimIndent()

        problem6b(
            """Time:      71530
Distance:  940200"""
        ) shouldBe 71503

        timing { problem6b(finalInput) shouldBe 27363861 }
    }

    @Test
    fun part7a() {
        problem7a(
            """
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
            """.trimIndent()
        ) shouldBe 6440

        timing { problem7a(input(23, 7)) shouldBe 248569531L }
    }

    @Test
    fun part7b() {
        problem7b(
            """
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
            """.trimIndent()
        ) shouldBe 5905

        timing { problem7b(input(23, 7)) shouldBe 250382098L }
    }

    @Test
    fun part8a() {
        problem8a(
            """
                RL

                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
        ) shouldBe 2

        problem8a(
            """
                LLR

                AAA = (BBB, BBB)
                BBB = (AAA, ZZZ)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
        ) shouldBe 6

        timing { problem8a(input(23, 8)) shouldBe 20221L }
    }

    @Test
    fun part8b() {
        problem8b(
            """
                LR

                11A = (11B, XXX)
                11B = (XXX, 11Z)
                11Z = (11B, XXX)
                22A = (22B, XXX)
                22B = (22C, 22C)
                22C = (22Z, 22Z)
                22Z = (22B, 22B)
                XXX = (XXX, XXX)
            """.trimIndent()
        ) shouldBe 6

        timing { problem8b(input(23, 8)) shouldBe 14616363770447L }
    }

    @Test
    fun part9a() {
        problem9a(
            """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()
        ) shouldBe 114

        timing { problem9a(input(23, 9)) shouldBe 1987402313L }
    }

    @Test
    fun part9b() {
        problem9b(
            """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()
        ) shouldBe 2

        timing { problem9b(input(23, 9)) shouldBe 900L }
    }

    @Test
    fun part10a() {
        problem10a(
            """
                -L|F7
                7S-7|
                L|7||
                -L-J|
                L|-JF
            """.trimIndent(),
            SOUTH
        ) shouldBe 4

        problem10a(
            """
                7-F7-
                .FJ|7
                SJLL7
                |F--J
                LJ.LJ
            """.trimIndent(),
            SOUTH
        ) shouldBe 8

        timing { problem10a(input(23, 10), SOUTH) shouldBe 7086 }
    }
}