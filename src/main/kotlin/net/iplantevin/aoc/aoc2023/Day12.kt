package net.iplantevin.aoc.aoc2023

object Day12 {

    // Todo: don't use bruteforce
    fun problem12a(input: String): Long {
        return input.lines().map { line ->
            val (springs, rawGroups) = line.split(" ")
            val groups = rawGroups.split(",").map { it.toInt() }
            springs to groups
        }.sumOf { (springs, groups) ->
            val totalArrangements = countArrangements(springs, groups, groups.sum())
            println("$springs: $totalArrangements")
            totalArrangements.toLong()
        }
    }

    // Does not perform, of course
    fun problem12b(input: String): Long {
        return input.lines().map { line ->
            val (springs, groups) = line.split(" ")
            val unfoldedSprings = List(5) { springs }.joinToString("?")
            val unfoldedGroups = List(5) { groups }.joinToString(",")
            unfoldedSprings to unfoldedGroups.split(",").map { it.toInt() }
        }.sumOf { (springs, groups) ->
            val totalArrangements = countArrangements(springs, groups, groups.sum())
            println("$springs: $totalArrangements")
            totalArrangements.toLong()
        }
    }

    private fun countArrangements(springs: String, groups: List<Int>, totalDamaged: Int): Int {
        if (springs.count { it == '#' } > totalDamaged) {
            return 0
        } else if (!springs.contains('?')) {
            val valid = isValidConfiguration(springs, groups, totalDamaged)
//            println("check $springs --> $valid")
            return if (valid) 1 else 0
        } else {
            val damaged = springs.replaceFirst('?', '#')
            val operational = springs.replaceFirst('?', '.')
            return countArrangements(damaged, groups, totalDamaged) +
                    countArrangements(operational, groups, totalDamaged)
        }
    }

    private fun isValidConfiguration(springs: String, groups: List<Int>, totalDamaged: Int): Boolean {
        if (springs.count { it == '#' } != totalDamaged) {
            return false
        }
        var springIndex = 0
        var groupIndex = 0
        while (springIndex < springs.length) {
            if (springs[springIndex] == '.') {
                springIndex++
            } else {
                if (groupIndex == groups.size) {
                    return false
                }
                if (springIndex + groups[groupIndex] > springs.length) {
                    return false
                }
                if (springs.substring(springIndex, springIndex + groups[groupIndex]).any { it != '#' }) {
                    return false
                }
                springIndex += groups[groupIndex]
                groupIndex++
                if (springs.getOrElse(springIndex) { '.' } != '.') {
                    return false
                }
            }
        }
        return groupIndex == groups.size
    }
}