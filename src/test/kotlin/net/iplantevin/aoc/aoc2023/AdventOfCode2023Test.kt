package net.iplantevin.aoc.aoc2023

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2023.AoC23_01.problem1a
import net.iplantevin.aoc.aoc2023.AoC23_01.problem1b
import net.iplantevin.aoc.util.readFile
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

        timing { problem1a(readFile("aoc23_1.txt")) shouldBe 54877 }
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

        timing { problem1b(readFile("aoc23_1.txt")) shouldBe 54100 }
    }
}