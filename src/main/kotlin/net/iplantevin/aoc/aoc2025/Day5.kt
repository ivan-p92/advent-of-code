package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.mergeWithOrNull
import net.iplantevin.aoc.common.size

object Day5 {

    fun part1(input: String): Int = parse(input).let { (idRanges, ingredientIds) ->
        ingredientIds.count { id -> idRanges.any { id in it } }
    }

    fun part2(input: String): Long = parse(input).let { (idRanges, _) ->
        idRanges.fold(emptyList<LongRange>()) { mergedRanges, nextRange ->
            val newMergedRanges = mutableListOf<LongRange>()
            var mergedRange = nextRange
            mergedRanges.forEach { range ->
                range.mergeWithOrNull(mergedRange)?.let { mergedRange = it } ?: newMergedRanges.add(range)
            }
            newMergedRanges.apply { add(mergedRange) }
        }.sumOf { it.size() }
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
