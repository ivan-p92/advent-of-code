package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Direction
import net.iplantevin.aoc.common.Direction.EAST
import net.iplantevin.aoc.common.Direction.NORTH
import net.iplantevin.aoc.common.Direction.SOUTH
import net.iplantevin.aoc.common.Direction.WEST
import net.iplantevin.aoc.common.Point
import kotlin.math.abs

object Day21 {

    fun part1(input: String): Long {
        val codes = input.split("\n")
        return codes.sumOf { code ->
            println("$code:")
            var position = numPad['A']!!
            val firstRobot = code.flatMap { button ->
                val targetButton = numPad[button]!!
                val diff = targetButton - position
                diffToMoves(diff, position).also {
                    position = targetButton
                }
            }
            printButtonPresses(firstRobot)
            position = dirPad['A']!!
            val secondRobot = firstRobot.flatMap { button ->
                val targetButton = dirPad[button?.arrow ?: 'A']!!
                val diff = targetButton - position
                diffToMoves(diff, position).also {
                    position = targetButton
                }
            }
            printButtonPresses(secondRobot)
            position = dirPad['A']!!
            val yourself = secondRobot.flatMap { button ->
                val targetButton = dirPad[button?.arrow ?: 'A']!!
                val diff = targetButton - position
                diffToMoves(diff, position).also {
                    position = targetButton
                }
            }
            printButtonPresses(yourself)
            println("Complexity: ${yourself.size} * ${code.take(3).toLong()} = ")
            val complexity = yourself.size * code.take(3).toLong()
            println()
            complexity
        }
    }

    fun part2(input: String): Long {
        TODO()
    }

    private fun printButtonPresses(presses: List<Direction?>) {
        presses.forEach { print(it?.arrow ?: 'A') }
        println()
    }

    private val numPad = mapOf(
        '7' to Point(0, 0),
        '8' to Point(1, 0),
        '9' to Point(2, 0),
        '4' to Point(0, 1),
        '5' to Point(1, 1),
        '6' to Point(2, 1),
        '1' to Point(0, 2),
        '2' to Point(1, 2),
        '3' to Point(2, 2),
        '0' to Point(1, 3),
        'A' to Point(2, 3),
    )

    private val dirPad = mapOf(
        '^' to Point(1, 0),
        'A' to Point(2, 0),
        '<' to Point(0, 1),
        'v' to Point(1, 1),
        '>' to Point(2, 1)
    )

    private fun diffToMoves(diff: Point, start: Point): List<Direction?> {
        if (diff == Point(0, 0)) return listOf(null)

        val moves = mutableListOf<Direction?>()
        when {
            diff.y != 0L && diff.x > 0 && start.x == 0L -> {
                repeat(diff.xInt) { moves += EAST }
                repeat(abs(diff.yInt)) { moves += if (diff.y < 0) NORTH else SOUTH }
            }

            diff.x < 0 && start.x <= 2 -> {
                repeat(abs(diff.yInt)) { moves += if (diff.y < 0) NORTH else SOUTH }
                repeat(abs(diff.xInt)) { moves += WEST }
            }

            else -> {
                repeat(abs(diff.xInt)) { moves += if (diff.x < 0) WEST else EAST }
                repeat(abs(diff.yInt)) { moves += if (diff.y < 0) NORTH else SOUTH }
            }
        }
        moves += null
        return moves
    }

    private fun diffToMovesOld(diff: Point): List<Direction?> {
        if (diff == Point(0, 0)) return listOf(null)

        val moves = mutableListOf<Direction?>()
        when {
            diff.x < 0 -> repeat(-1 * diff.xInt) { moves += WEST }
            diff.x > 0 -> repeat(diff.xInt) { moves += EAST }
        }
        when {
            diff.y < 0 -> repeat(-1 * diff.yInt) { moves += NORTH }
            diff.y > 0 -> repeat(diff.yInt) { moves += SOUTH }
        }
        moves += null
        return moves
    }
}
