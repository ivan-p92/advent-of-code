package net.iplantevin.aoc.aoc2022

object Day1 {

    fun part1(input: String): Int {
        return input.split("\n\n").maxOf { elf -> elf.lines().sumOf { it.toInt() } }
    }

    fun part2(input: String): Int {
        return input.split("\n\n").map { elf -> elf.lines().sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)
            .sum()
    }
}
