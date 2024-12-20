package net.iplantevin.aoc.aoc2024

object Day11 {

    fun solve(input: String, blinks: Int): Long {
        val stones = input.split(" ").map { it.toLong() }
        val blinkStoneCountCache = mutableMapOf<CacheKey, Long>()
        return stones.sumOf { stone -> countStonesAfterBlinks(stone, blinks, blinkStoneCountCache) }
    }

    private fun countStonesAfterBlinks(stone: Long, blinks: Int, cache: MutableMap<CacheKey, Long>): Long {
        return when (blinks) {
            0 -> 1
            else -> cache.getOrPut(CacheKey(stone, blinks)) {
                when {
                    stone == 0L -> {
                        countStonesAfterBlinks(1L, blinks - 1, cache)
                    }

                    "$stone".length % 2 == 0 -> {
                        val s = "$stone"
                        val firstStone = s.dropLast(s.length / 2).toLong()
                        val secondStone = s.drop(s.length / 2).toLong()
                        countStonesAfterBlinks(firstStone, blinks - 1, cache) +
                                countStonesAfterBlinks(secondStone, blinks - 1, cache)
                    }

                    else -> {
                        countStonesAfterBlinks(stone * 2024, blinks - 1, cache)
                    }
                }
            }
        }
    }

    private data class CacheKey(val stone: Long, val blinks: Int)
}
