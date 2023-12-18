package net.iplantevin.aoc.aoc2023

import kotlin.math.max

object Day2 {
    private val gameRegex = """Game (\d+):(.*)""".toRegex()
    private val redRegex = """.* (\d+) red.*""".toRegex()
    private val greenRegex = """.* (\d+) green.*""".toRegex()
    private val blueRegex = """.* (\d+) blue.*""".toRegex()

    fun problem2a(games: String, red: Int, green: Int, blue: Int): Int {
        return games.lines()
            .map { it.toGame() }
            .filter { it.matches(red, green, blue) }
            .sumOf { it.id }
    }

    fun problem2b(games: String): Int {
        return games.lines().sumOf { it.toGame().power() }
    }

    private fun String.toGame(): Game {
        val match = checkNotNull(gameRegex.matchEntire(this)) { "line should match game regex" }
        check(match.groups.size == 3) { "there should be three groups" }
        val id = match.groups[1]!!.value.toInt()
        val draws = match.groups[2]!!.value.split(";")
            .map { draw ->
                val red = redRegex.matchEntire(draw)?.groups?.get(1)?.value?.toInt() ?: 0
                val green = greenRegex.matchEntire(draw)?.groups?.get(1)?.value?.toInt() ?: 0
                val blue = blueRegex.matchEntire(draw)?.groups?.get(1)?.value?.toInt() ?: 0
                RGB(red, green, blue)
            }
        return Game(id, draws)
    }

    data class Game(val id: Int, val draws: List<RGB>) {
        fun matches(red: Int, green: Int, blue: Int): Boolean {
            return draws.none { it.red > red || it.green > green || it.blue > blue }
        }

        fun power(): Int {
            val highestCounts = draws.fold(RGB(0, 0, 0)) { acc, draw ->
                RGB(max(acc.red, draw.red), max(acc.green, draw.green), max(acc.blue, draw.blue))
            }
            return with(highestCounts) { red * green * blue }
        }
    }

    data class RGB(val red: Int, val green: Int, val blue: Int)
}
