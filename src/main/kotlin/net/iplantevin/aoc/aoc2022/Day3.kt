package net.iplantevin.aoc.aoc2022

object Day3 {

    fun part1(input: String): Int {
        return input.lines().sumOf { line ->
            val firstHalf = line.take(line.length / 2)
            val secondHalf = line.takeLast(line.length / 2)
            firstHalf.toList().intersect(secondHalf.toSet()).first().priority()
        }
    }

    fun part2(input: String): Int {
        val groups = mutableListOf<List<String>>()
        var currentGroup = mutableListOf<String>()
        input.lines().forEachIndexed { index, rucksack ->
            if (index % 3 == 0) {
                currentGroup = mutableListOf()
                groups += currentGroup
            }
            currentGroup += rucksack
        }
        return groups.sumOf { group ->
            group.first().toList().intersect(group[1].toSet())
                .intersect(group[2].toSet()).first().priority()
        }
    }

    private fun Char.priority(): Int = if (isUpperCase()) {
        code - 38
    } else {
        code - 96
    }
}
