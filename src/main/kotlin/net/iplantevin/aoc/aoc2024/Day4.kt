package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.ArrayGrid
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.common.toCharArrayGrid

object Day4 {
    private val steps = listOf(
        Point(1, -1),
        Point(1, 0),
        Point(1, 1),
        Point(-1, -1),
        Point(-1, 0),
        Point(-1, 1),
        Point(0, 1),
        Point(0, -1)
    )

    private val diagonalSteps = listOf(
        Point(-1, -1),
        Point(1, 1),
        Point(1, -1),
        Point(-1, 1),
    )

    fun part1(input: String): Int {
        val wordGrid = input.toCharArrayGrid()
        var matches = 0

        wordGrid.forEachIndexed { y, line ->
            line.forEachIndexed { x, letter ->
                if (letter == 'X') {
                    matches += countXmas(wordGrid, Point(x, y))
                }
            }
        }
        return matches
    }

    fun part2(input: String): Int {
        val wordGrid = input.toCharArrayGrid()
        var matches = 0

        wordGrid.forEachIndexed { y, line ->
            line.forEachIndexed { x, letter ->
                if (letter == 'A' && isXmas(wordGrid, Point(x, y))) {
                    matches++
                }
            }
        }
        return matches
    }

    private fun countXmas(wordGrid: ArrayGrid<Char>, point: Point): Int {
        var matches = 0
        steps.forEach { step ->
            var word = ""
            for (i in 1..3) {
                val nextLetter = (step * i) + point
                word += wordGrid.getOrNull(nextLetter.yInt)?.getOrNull(nextLetter.xInt)
            }
            if (word == "MAS") matches++
        }
        return matches
    }

    private fun isXmas(wordGrid: ArrayGrid<Char>, point: Point): Boolean {
        var diagonals = ""
        diagonalSteps.forEach { step ->
            val nextLetter = step + point
            diagonals += wordGrid.getOrNull(nextLetter.yInt)?.getOrNull(nextLetter.xInt)
        }
        return when (diagonals) {
            "MSMS", "MSSM", "SMSM", "SMMS" -> true
            else -> false
        }
    }
}
