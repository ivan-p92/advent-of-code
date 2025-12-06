package net.iplantevin.aoc.aoc2025

object Day6 {

    fun part1(input: String): Long = parse1(input).sumOf { it.answer() }

    fun part2(input: String): Long = parse2(input).sumOf { it.answer() }

    private fun parse1(input: String): List<Problem> {
        val splitLines = input.lines().map { it.trim().split("\\s+".toRegex()) }
        val numberLines = splitLines.dropLast(1)
        val operations = splitLines.last().map { it.first() }
        return numberLines.first().indices.map { index ->
            val numbers = numberLines.map { it[index].toLong() }
            Problem(numbers, operations[index])
        }
    }

    private fun parse2(input: String): List<Problem> {
        val numberLines = input.lines().dropLast(1)
        val operations = input.lines().last()
        var currentOp = '.'
        var currentNumbers = mutableListOf<Long>()
        val problems = mutableListOf<Problem>()
        val maxLineLength = numberLines.maxOf { it.length }

        (0..<maxLineLength).forEach { index ->
            val operation = operations.getOrNull(index)
            if (operation == '*' || operation == '+') {
                if (currentNumbers.isNotEmpty()) {
                    problems += Problem(currentNumbers, currentOp)
                }
                currentOp = operation
                currentNumbers = mutableListOf()
            }
            val numberColumn = numberLines.mapNotNull { it.getOrNull(index)?.let { c -> if (c == ' ') null else c } }
            if (numberColumn.isNotEmpty()) {
                currentNumbers += numberColumn.joinToString("").toLong()
            }
        }
        problems += Problem(currentNumbers, currentOp)
        return problems
    }

    private data class Problem(val numbers: List<Long>, val operation: Char) {

        fun answer(): Long = numbers.reduce { acc, v ->
            if (operation == '*') acc * v else acc + v
        }
    }
}
