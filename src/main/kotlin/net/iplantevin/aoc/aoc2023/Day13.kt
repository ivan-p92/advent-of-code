package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Grid
import kotlin.math.min

object Day13 {

    fun problem13(input: String, checkSmudges: Boolean = false): Int {
        val patterns = input.split("""\n\n""".toRegex()).map { Pattern(it).apply { findReflection(checkSmudges) } }
        val (leftColumns, topRows) = patterns.fold(0 to 0) { acc, pattern ->
            acc.first + pattern.leftColumns to acc.second + pattern.topRows
        }
        return leftColumns + 100 * topRows
    }

    private class Pattern(rawPattern: String) {

        val rows: Grid<Char> = rawPattern.lines().map { it.toList() }
        val width = rows.first().size
        val height = rows.size
        var leftColumns = 0
            private set
        var topRows = 0
            private set

        val columns: Grid<Char> = (0 until width).map { x ->
            (0 until height).map { y ->
                rows[y][x]
            }
        }

        fun findReflection(checkSmudges: Boolean) {
            hasReflection(columns, checkSmudges)?.let { leftColumns = it }
                ?: hasReflection(rows, checkSmudges)?.let { topRows = it }
        }

        private fun hasReflection(rowsOrColumns: Grid<Char>, checkSmudges: Boolean): Int? {
            for (index in 0 until rowsOrColumns.size - 1) {
                if (rowsOrColumns[index] == rowsOrColumns[index + 1]
                    || hasSmudge(rowsOrColumns[index], rowsOrColumns[index + 1], checkSmudges)
                ) {
                    if (isPerfectReflection(rowsOrColumns, index + 1, checkSmudges)) {
                        return index + 1
                    }
                }
            }
            return null
        }

        private fun isPerfectReflection(
            rowsOrColumns: Grid<Char>,
            lineIndex: Int,
            checkSmudges: Boolean
        ): Boolean {
            var hasSmudge = false
            val firstHalf = rowsOrColumns.subList(0, lineIndex).reversed()
            val secondHalf = rowsOrColumns.subList(lineIndex, rowsOrColumns.size)

            repeat(min(firstHalf.size, secondHalf.size)) { index ->
                if (firstHalf[index] != secondHalf[index]) {
                    if (!checkSmudges || hasSmudge) {
                        // There can only be one smudge overall, if checking for smudges
                        return false
                    }
                    hasSmudge = hasSmudge(firstHalf[index], secondHalf[index])
                }
            }
            return !(checkSmudges && !hasSmudge)
        }

        private fun hasSmudge(first: List<Char>, second: List<Char>, checkSmudges: Boolean = true): Boolean {
            if (!checkSmudges) return false

            var hasSmudge = false
            first.forEachIndexed { index, char ->
                if (char != second[index]) {
                    if (hasSmudge) {
                        // There can be only exactly one different char
                        return false
                    }
                    hasSmudge = true
                }
            }
            return hasSmudge
        }
    }
}
