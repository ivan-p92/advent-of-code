package net.iplantevin.aoc.aoc2022

object Day2 {

    fun part1(input: String): Int {
        return parseRounds(input).sumOf { it.score() }
    }

    fun part2(input: String): Int {
        return input.lines().map {
            it.split(" ").let { (opponent, strategy) ->
                val own = Shape.forStrategy(strategy, opponent)
                Round(Shape.of(opponent), own)
            }
        }.sumOf { it.score() }
    }

    private fun parseRounds(input: String): List<Round> {
        return input.lines().map {
            it.split(" ").let { (opponent, own) ->
                Round(Shape.of(opponent), Shape.of(own))
            }
        }
    }

    private data class Round(val opponent: Shape, val own: Shape) {

        fun score(): Int = own.score(opponent)
    }

    private sealed interface Shape {
        fun score(other: Shape): Int

        fun losesFrom(): Shape

        fun winsFrom(): Shape

        companion object {
            fun of(code: String): Shape {
                return when (code) {
                    "A", "X" -> Rock
                    "B", "Y" -> Paper
                    else -> Scissors
                }
            }

            fun forStrategy(strategy: String, opponent: String): Shape {
                return when (strategy) {
                    "X" -> of(opponent).winsFrom()
                    "Y" -> of(opponent)
                    else -> of(opponent).losesFrom()
                }
            }
        }
    }

    private object Rock : Shape {
        override fun score(other: Shape): Int {
            return when (other) {
                Rock -> 1 + 3
                Scissors -> 1 + 6
                else -> 1
            }
        }

        override fun losesFrom(): Shape = Paper

        override fun winsFrom(): Shape = Scissors
    }

    private object Paper : Shape {
        override fun score(other: Shape): Int {
            return when (other) {
                Paper -> 2 + 3
                Rock -> 2 + 6
                else -> 2
            }
        }

        override fun losesFrom(): Shape = Scissors

        override fun winsFrom(): Shape = Rock
    }

    private object Scissors : Shape {
        override fun score(other: Shape): Int {
            return when (other) {
                Scissors -> 3 + 3
                Paper -> 3 + 6
                else -> 3
            }
        }

        override fun losesFrom(): Shape = Rock

        override fun winsFrom(): Shape = Paper
    }
}