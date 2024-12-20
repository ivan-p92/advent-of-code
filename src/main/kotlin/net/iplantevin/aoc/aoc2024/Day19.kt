package net.iplantevin.aoc.aoc2024

object Day19 {

    fun part1(input: String): Int = parse(input).let { (towels, designs) ->
        designs.count { design -> countPossibilities(design, towels, mutableMapOf()) > 0L }
    }

    fun part2(input: String): Long = parse(input).let { (towels, designs) ->
        designs.sumOf { design -> countPossibilities(design, towels, mutableMapOf()) }
    }

    private fun countPossibilities(design: String, towels: List<String>, cache: MutableMap<String, Long>): Long {
        return when {
            design.isEmpty() -> 1
            else -> cache.getOrPut(design) {
                towels.filter { towel -> design.startsWith(towel) }.sumOf { towel ->
                    countPossibilities(design.drop(towel.length), towels, cache)
                }
            }
        }
    }

    private fun parse(input: String): Pair<List<String>, List<String>> {
        return input.split("\n\n").let { (towels, designs) ->
            towels.split(", ") to designs.lines()
        }
    }
}
