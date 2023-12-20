package net.iplantevin.aoc.aoc2023

object Day6 {

    fun problem6a(input: String): Int {
        val races = input.parseRaces()
        return races.map { it.waysToWin() }.reduce(Int::times)
    }

    fun problem6b(input: String): Int {
        // This is relatively slow, it could be optimized to find the first and last time at which
        // the record is broken, but with just ~50ms of runtime this way, it doesn't really matter.
        val race = input.parseRaces().first()
        return race.waysToWin()
    }

    private fun String.parseRaces(): List<Race> {
        val times = lines()[0].split(" ").drop(1).mapNotNull { it.toLongOrNull() }
        val distances = lines()[1].split(" ").drop(1).mapNotNull { it.toLongOrNull() }
        return times.zip(distances).map { (time, distance) ->
            Race(time, distance)
        }
    }

    private data class Race(val time: Long, val recordDistance: Long) {

        fun waysToWin(): Int {
            return (1 until time).count { distanceTravelled(it) > recordDistance }
        }

        private fun distanceTravelled(holdTime: Long) = holdTime * (time - holdTime)
    }
}