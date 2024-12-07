package net.iplantevin.aoc.aoc2023

object Day12 {

    private val stateCountCache = mutableMapOf<State, Long>()
    private var cacheHits = 0

    fun part1(input: String): Long {
        return input.lines().map { line ->
            val (springs, rawGroups) = line.split(" ")
            val groups = rawGroups.split(",").map { it.toInt() }
            springs to groups
        }.sumOf { (springs, groups) ->
            stateCountCache.clear()
            val totalArrangements = arrangements(springs, groups)
//            println("$springs: $totalArrangements arrangements, $cacheHits cache hits")
            totalArrangements
        }.also {
            println("Total cache hits: $cacheHits")
        }
    }

    fun part2(input: String): Long {
        return input.lines().map { line ->
            val (springs, groups) = line.split(" ")
            val unfoldedSprings = List(5) { springs }.joinToString("?")
            val unfoldedGroups = List(5) { groups }.joinToString(",")
            unfoldedSprings to unfoldedGroups.split(",").map { it.toInt() }
        }.sumOf { (springs, groups) ->
            stateCountCache.clear()
            val totalArrangements = arrangements(springs, groups)
//            println("$springs: $totalArrangements arrangements, $cacheHits cache hits")
            totalArrangements
        }.also {
            println("Total cache hits: $cacheHits")
        }
    }

    private fun arrangements(springs: String, groups: List<Int>, state: State = State()): Long {
        if (stateCountCache.contains(state)) {
            cacheHits++
            return stateCountCache[state]!!
        }

        // Reached the end of the springs
        if (state.position == springs.length) {
            if (state.length != 0 && state.length != groups[state.group]) {
                // Incomplete arrangement
                return 0L
            } else {
                val finalGroup = if (state.length == groups.getOrNull(state.group)) state.group + 1 else state.group
                return if (finalGroup == groups.size) 1 else 0
            }
        }

        var arrangements = 0L
        val spring = springs[state.position]

        if (spring != '.' && state.group < groups.size && state.length < groups[state.group]) {
            // A valid damaged spring
            arrangements += arrangements(
                springs,
                groups,
                State(
                    position = state.position + 1,
                    group = state.group,
                    length = state.length + 1,
                )
            )
        }

        if (spring != '#' && (state.length == 0 || state.length == groups[state.group])) {
            // A valid operational spring
            arrangements += arrangements(
                springs,
                groups,
                State(
                    position = state.position + 1,
                    group = if (state.length == groups.getOrNull(state.group)) state.group + 1 else state.group,
                    length = 0,
                )
            )
        }
        stateCountCache[state] = arrangements
        return arrangements
    }

    private data class State(
        val position: Int = 0,
        val group: Int = 0,
        val length: Int = 0,
    )
}
