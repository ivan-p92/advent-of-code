package net.iplantevin.aoc.aoc2024

import kotlin.math.abs

object Day1 {

    fun part1(input: String): Long {
        val (leftList, rightList) = parseLists(input)
        val leftSorted = leftList.sorted()
        val rightSorted = rightList.sorted()
        return leftSorted.foldIndexed(0L) { index, total, number ->
            total + abs(number - rightSorted[index])
        }
    }

    fun part2(input: String): Long {
        val (leftList, rightList) = parseLists(input)
        val counts = rightList.groupingBy { it }.eachCount()
        return leftList.fold(0) { total: Long, number: Long ->
            total + number * counts.getOrDefault(number, 0)
        }
    }

    private fun parseLists(input: String): Pair<List<Long>, List<Long>> {
        val leftList = mutableListOf<Long>()
        val rightList = mutableListOf<Long>()
        input.lineSequence().forEach { line ->
            line.split("   ").let { (left, right) ->
                leftList += left.toLong()
                rightList += right.toLong()
            }
        }
        return Pair(leftList, rightList)
    }
}
