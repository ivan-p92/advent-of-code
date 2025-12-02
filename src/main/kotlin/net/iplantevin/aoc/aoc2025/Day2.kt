package net.iplantevin.aoc.aoc2025

object Day2 {

    fun part1(input: String): Long = parse(input).flatMap { range ->
        findInvalidIds(range)
    }.sum()

    fun part2(input: String): Long = parse(input).flatMap { range ->
        findInvalidIds2(range)
    }.sum()

    private fun parse(input: String): List<LongRange> = input.split(",").map {
        it.split("-").let { (start, end) ->
            LongRange(start.toLong(), end.toLong())
        }
    }

    private fun findInvalidIds(range: LongRange): List<Long> {
        return range.filter { id ->
            val idString = id.toString()
            idString.length % 2 == 0 && idString.take(idString.length / 2) == idString.takeLast(idString.length / 2)
        }
    }

    private fun findInvalidIds2(range: LongRange): List<Long> {
        // It's not smart or fast, but it works
        return range.filter { id ->
            splitInEqualParts(id).any { it.size == 1 }
        }
    }

    private fun splitInEqualParts(id: Long): Sequence<Set<String>> {
        val idString = id.toString()
        val startSize = idString.length / 2
        return sequence {
            for (size in startSize downTo 1) {
                if (idString.length % size == 0) {
                    val set = mutableSetOf<String>()
                    repeat(idString.length / size) { index ->
                        set += idString.substring(index * size, (index + 1) * size)
                    }
                    yield(set)
                }
            }
        }
    }
}
