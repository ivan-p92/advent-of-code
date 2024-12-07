package net.iplantevin.aoc.aoc2023

object Day9 {

    fun part1(input: String): Long {
        return input.parseSequences().sumOf { extrapolateForwards(it) }
    }

    fun part2(input: String): Long {
        return input.parseSequences().sumOf { extrapolateBackwards(it) }
    }

    private fun String.parseSequences(): List<List<Long>> =
        lines().map { line -> line.split(" ").map { it.toLong() } }

    private fun extrapolateForwards(sequence: List<Long>): Long {
        var allZeros: Boolean
        val lastNumbers = mutableListOf(sequence.last())
        var currentSequence = sequence
        do {
            allZeros = true
            currentSequence = currentSequence.zipWithNext { a, b ->
                (b - a).also { if (it != 0L) allZeros = false }
            }
            lastNumbers += currentSequence.last()
        } while (!allZeros)
        return lastNumbers.sum()
    }

    private fun extrapolateBackwards(sequence: List<Long>): Long {
        var allZeros: Boolean
        val firstNumbers = mutableListOf(sequence.first())
        var currentSequence = sequence
        do {
            allZeros = true
            currentSequence = currentSequence.zipWithNext { a, b ->
                (b - a).also { if (it != 0L) allZeros = false }
            }
            firstNumbers += currentSequence.first()
        } while (!allZeros)
        return firstNumbers.foldRight(0L) { number, acc ->
            number - acc
        }
    }
}

