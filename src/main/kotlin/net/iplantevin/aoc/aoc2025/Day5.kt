package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.mergeWithOrNull
import net.iplantevin.aoc.common.size

object Day5 {

    fun part1(input: String): Int {
        val (idRanges, ingredientIds) = parse(input)
        return ingredientIds.count { id -> idRanges.any { id in it } }
    }

    fun part2(input: String): Long {
        val (idRanges, _) = parse(input)
        var mergedRanges = idRanges
        while (true) {
            val newMergedRanges = mutableListOf<LongRange>()
            newMergedRanges += mergedRanges
            loop@ for (firstRangeIndex in newMergedRanges.indices) {
                for (otherRangeIndex in newMergedRanges.indices) {
                    val firstRange = newMergedRanges[firstRangeIndex]
                    val otherRange = newMergedRanges[otherRangeIndex]
                    val mergedRange = firstRange.mergeWithOrNull(otherRange)

                    if (firstRangeIndex != otherRangeIndex && mergedRange != null) {
                        newMergedRanges[firstRangeIndex] = mergedRange
                        newMergedRanges.removeAt(otherRangeIndex)
                        break@loop
                    }
                }
            }
            if (newMergedRanges.size == mergedRanges.size) break
            mergedRanges = newMergedRanges
        }
        return mergedRanges.sumOf { it.size() }
    }

    private fun parse(input: String): Pair<List<LongRange>, List<Long>> {
        val (stringIdRanges, stringIngredientIds) = input.split("\n\n");
        val idRanges = stringIdRanges.lines().map {
            val (first, last) = it.split("-")
            LongRange(first.toLong(), last.toLong())
        }
        val ingredientIds = stringIngredientIds.lines().map { it.toLong() }
        return idRanges to ingredientIds
    }
}
