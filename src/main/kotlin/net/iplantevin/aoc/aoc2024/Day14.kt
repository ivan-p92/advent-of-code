package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Point

object Day14 {

    fun part1(input: String, width: Int, height: Int): Int {
        val movedGuards = parseGuards(input).map { move(it, 100, width, height) }
        return calculateSafetyFactor(movedGuards, width, height)
    }

    fun part2(input: String): Int {
        var guards = parseGuards(input)
        var smallestSafetyFactor = Int.MAX_VALUE
        var safestGuards = guards
        var safestSeconds = 0

        for (seconds in 1 until (7_000)) {
            guards = guards.map { move(it, 1, 101, 103) }
            val safetyFactor = calculateSafetyFactor(guards, 101, 103)
            if (safetyFactor < smallestSafetyFactor) {
                smallestSafetyFactor = safetyFactor
                safestGuards = guards
                safestSeconds = seconds
            }
        }
        val safestGuardPositions = safestGuards.map { it.position }.toSet()
        printGuards(safestGuardPositions, safestSeconds, 101, 103)
        return safestSeconds
    }

    private fun move(guard: Guard, seconds: Int, width: Int, height: Int): Guard {
        val newX = (width + (guard.position.x + guard.velocity.x * seconds) % width) % width
        val newY = (height + (guard.position.y + guard.velocity.y * seconds) % height) % height
        return guard.copy(position = Point(newX, newY))
    }

    private fun calculateSafetyFactor(guards: List<Guard>, width: Int, height: Int): Int {
        var q1 = 0
        var q2 = 0
        var q3 = 0
        var q4 = 0
        guards.forEach { guard ->
            when {
                guard.position.x < width / 2 && guard.position.y < height / 2 -> q1++
                guard.position.x > width / 2 && guard.position.y < height / 2 -> q2++
                guard.position.x < width / 2 && guard.position.y > height / 2 -> q3++
                guard.position.x > width / 2 && guard.position.y > height / 2 -> q4++
            }
        }
        return q1 * q2 * q3 * q4
    }

    private fun printGuards(guardedPositions: Set<Point>, seconds: Int, width: Int, height: Int) {
        println("Guard positions after $seconds seconds:")
        repeat(height) { y ->
            repeat(width) { x ->
                if (Point(x, y) in guardedPositions) {
                    print("O")
                } else {
                    print(" ")
                }
            }
            println()
        }
    }

    private fun parseGuards(input: String): List<Guard> {
        val guardRegex = """p=(.+),(.+) v=(.+),(.+)""".toRegex()
        return input.lines().map { line ->
            val groups = guardRegex.matchEntire(line)!!.groupValues
            val start = Point(groups[1].toLong(), groups[2].toLong())
            val velocity = Point(groups[3].toLong(), groups[4].toLong())
            Guard(start, velocity)
        }
    }

    private data class Guard(val position: Point, val velocity: Point)
}
