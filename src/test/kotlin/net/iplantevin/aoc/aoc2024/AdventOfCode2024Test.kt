package net.iplantevin.aoc.aoc2024

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.aoc2024.Day1.problem1a
import net.iplantevin.aoc.aoc2024.Day1.problem1b
import net.iplantevin.aoc.aoc2024.Day2.problem2a
import net.iplantevin.aoc.aoc2024.Day2.problem2b
import net.iplantevin.aoc.aoc2024.Day3.problem3a
import net.iplantevin.aoc.aoc2024.Day3.problem3b
import net.iplantevin.aoc.aoc2024.Day4.problem4a
import net.iplantevin.aoc.aoc2024.Day4.problem4b
import net.iplantevin.aoc.aoc2024.Day5.problem5a
import net.iplantevin.aoc.aoc2024.Day5.problem5b
import net.iplantevin.aoc.aoc2024.Day6.problem6a
import net.iplantevin.aoc.aoc2024.Day6.problem6b
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

    @Test
    fun part4a() {
        problem4a(
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """.trimIndent()
        ) shouldBe 18

        timing { problem4a(input(24, 4)) shouldBe 2464 }
    }

    @Test
    fun part4b() {
        problem4b(
            """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
            """.trimIndent()
        ) shouldBe 9

        timing { problem4b(input(24, 4)) shouldBe 1982 }
    }

    @Test
    fun part5a() {
        problem5a(
            """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.trimIndent()
        ) shouldBe 143

        timing { problem5a(input(24, 5)) shouldBe 6267 }
    }

    @Test
    fun part5b() {
        problem5b(
            """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.trimIndent()
        ) shouldBe 123

        timing { problem5b(input(24, 5)) shouldBe 5184 }
    }

    @Test
    fun part6a() {
        problem6a(
            """""".trimIndent()
        ) shouldBe 0
        
        timing { problem6a(input(24, 6)) shouldBe 0 }
    }

    @Test
    fun part6b() {
        problem6b(
            """""".trimIndent()
        ) shouldBe 0

        timing { problem6b(input(24, 6)) shouldBe 0 }
    }
}
