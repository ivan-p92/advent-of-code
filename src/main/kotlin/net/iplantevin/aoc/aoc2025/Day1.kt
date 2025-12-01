package net.iplantevin.aoc.aoc2025

import kotlin.math.abs

object Day1 {

    fun part1(input: String): Int = solve(input) { _, _, next ->
        if (next == 0) 1 else 0
    }

    fun part2(input: String): Int = solve(input) { current, move, next ->
        var zeroes = 0
        if (next == 0) zeroes++

        val fullTurns = abs(move / 100)
        zeroes += fullTurns

        val netMove = move % 100
        if (netMove < 0 && current > 0 && current + netMove < 0
            || netMove > 0 && netMove + current > 100
        ) zeroes++
        zeroes
    }

    private fun solve(input: String, counter: (current: Int, move: Int, next: Int) -> Int): Int {
        var zeroes = 0
        input.replace("L", "-")
            .replace("R", "")
            .lines().map { it.toInt() }.fold(50) { current, move ->
                next(current, move).also { next ->
                    zeroes += counter(current, move, next)
                }
            }
        return zeroes
    }

    private fun next(current: Int, move: Int): Int = (current + (move % 100) + 100) % 100
}
