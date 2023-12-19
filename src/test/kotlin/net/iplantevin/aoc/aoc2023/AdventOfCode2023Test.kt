package net.iplantevin.aoc.aoc2023

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2023.Day1.problem1a
import net.iplantevin.aoc.aoc2023.Day1.problem1b
import net.iplantevin.aoc.aoc2023.Day2.problem2a
import net.iplantevin.aoc.aoc2023.Day2.problem2b
import net.iplantevin.aoc.aoc2023.Day3.problem3a
import net.iplantevin.aoc.aoc2023.Day3.problem3b
import net.iplantevin.aoc.aoc2023.Day4.problem4a
import net.iplantevin.aoc.aoc2023.Day4.problem4b
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
}