package net.iplantevin.aoc.aoc2022

object Day5 {

    fun part1(input: String): String = solve(input, moveOneByOne = true)

    fun part2(input: String): String = solve(input, moveOneByOne = false)

    private fun solve(input: String, moveOneByOne: Boolean): String {
        val stacks = parseStacks(input)
        val moves = parseMoves(input)
        moves.forEach { (size, fromIndex, toIndex) ->
            val newStack = stacks[fromIndex].take(size).let { cratesToMove ->
                if (moveOneByOne) {
                    cratesToMove.reversed()
                } else {
                    cratesToMove
                }
            }.toMutableList()
            newStack += stacks[toIndex]
            stacks[toIndex] = newStack
            stacks[fromIndex] = stacks[fromIndex].drop(size).toMutableList()
        }
        return stacks.map { it.first() }.joinToString(separator = "")
    }

    private fun parseStacks(input: String): MutableList<MutableList<Char>> {
        val stackDrawing = input.split("\n\n").first()
        val stackColumns = stackDrawing.lines().last().mapIndexedNotNull { i, ch ->
            if (ch.isDigit()) {
                i
            } else {
                null
            }
        }
        val stacks = MutableList(stackColumns.size) { mutableListOf<Char>() }

        stackDrawing.lines().dropLast(1).forEach { line ->
            stackColumns.forEachIndexed { stackNumber, column ->
                if (line.getOrElse(column) { ' ' } != ' ') {
                    stacks[stackNumber] += line[column]
                }
            }
        }
        return stacks
    }

    private fun parseMoves(input: String): List<Move> {
        val pattern = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        return input.split("\n\n")[1].lines().map { move ->
            val (_, size, fromIndex, toIndex) = pattern.matchEntire(move)!!.groupValues
            Move(size.toInt(), fromIndex.toInt() - 1, toIndex.toInt() - 1)
        }
    }

    private data class Move(val size: Int, val fromIndex: Int, val toIndex: Int)
}
