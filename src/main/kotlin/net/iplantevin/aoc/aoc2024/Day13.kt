package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.Point

object Day13 {

    fun part1(input: String): List<Long?> = parseMachines(input).map { machine ->
        machine.solve()?.let { (aPresses, bPresses) ->
            aPresses * 3 + bPresses
        }
    }

    fun part2(input: String): List<Long?> = parseMachines(input, 10_000_000_000_000).map { machine ->
        machine.solve()?.let { (aPresses, bPresses) ->
            aPresses * 3 + bPresses
        }
    }

    private fun ClawMachine.solve(): Point? {
        // Solved by refreshing my math with the help of https://quickmath.com/math-tutorials/solving-a-system-of-two-linear-equations.html
        // Given two equations:
        // a * xA + b * xB = X
        // a * yA + b * yB = Y
        // Where xA, yA, xB, yB are the steps button A and B take respectively for each press.
        // Step 1: multiply the X equation by yB, and Y equation by -xB, resulting in an equal b factor for X and Y.
        var x = prize.x * b.y
        var y = prize.y * -1 * b.x
        val (xA, bFactor) = Point(a.x, b.x) * b.y
        val (yA, _) = Point(a.y, b.y) * (b.x * -1)

        // Step 2: eliminate b from Y, solving a. If it's not dividable without a remainder, we can't solve this equation.
        y += x
        val aFactor = yA + xA
        if (y % aFactor != 0L) return null
        val solvedA = y / aFactor

        // Step 3: eliminate a from X, solving b.
        x -= xA * solvedA
        if (x % bFactor != 0L) return null
        val solvedB = x / bFactor

        return Point(solvedA, solvedB)
    }

    private fun parseMachines(input: String, offset: Long = 0): List<ClawMachine> {
        val buttonRegex = """.*X\+(\d+), Y\+(\d+)""".toRegex()
        val prizeRegex = """.*X=(\d+), Y=(\d+)""".toRegex()
        return input.split("\n\n").map { machine ->
            val (aLine, bLine, prizeLine) = machine.split("\n")
            val (_, aX, aY) = buttonRegex.matchEntire(aLine)!!.groupValues
            val (_, bX, bY) = buttonRegex.matchEntire(bLine)!!.groupValues
            val (_, prizeX, prizeY) = prizeRegex.matchEntire(prizeLine)!!.groupValues
            ClawMachine(
                Point(aX.toLong(), aY.toLong()),
                Point(bX.toLong(), bY.toLong()),
                Point(prizeX.toLong() + offset, prizeY.toLong() + offset)
            )
        }
    }

    private data class ClawMachine(val a: Point, val b: Point, val prize: Point)
}
