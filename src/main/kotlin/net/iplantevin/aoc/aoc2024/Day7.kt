package net.iplantevin.aoc.aoc2024

import net.iplantevin.aoc.common.mapParallel
import java.util.*

object Day7 {

    fun part1(input: String): Long {
        val operators = listOf<(Long, Long) -> Long>(Long::plus, Long::times)
        return sumOfComputableEquations(input, operators)
    }

    fun part2(input: String): Long {
        val operators = listOf<(Long, Long) -> Long>(Long::plus, Long::times, { a, b -> "$a$b".toLong() })
        return sumOfComputableEquations(input, operators)
    }

    private fun sumOfComputableEquations(
        input: String,
        operators: List<(Long, Long) -> Long>
    ): Long = parseEquations(input).mapParallel { equation ->
        if (equation.doesCompute(operators)) equation.result else 0
    }.sum()

    private fun Equation.doesCompute(operators: List<(Long, Long) -> Long>): Boolean {
        val queue = LinkedList<State>()
        val a = values[0]
        val b = values[1]
        operators.forEach { op ->
            queue += State(op(a, b), values.drop(2))
        }
        while (queue.isNotEmpty()) {
            val state = queue.pop()
            if (state.total == result && state.remainingValues.isEmpty()) {
                return true
            }
            if (state.remainingValues.isEmpty()) {
                continue
            }
            val remainingValues = state.remainingValues.drop(1)
            operators.forEach { op ->
                val total = op(state.total, state.remainingValues.first())
                if (total <= result) queue.push(State(total, remainingValues))
            }
        }
        return false
    }

    private fun parseEquations(input: String): List<Equation> {
        return input.lines().map { line ->
            val (result, values) = line.split(": ")
            Equation(
                result = result.toLong(),
                values = values.split(" ").map { it.toLong() }
            )
        }
    }

    private data class Equation(val result: Long, val values: List<Long>)

    private data class State(val total: Long, val remainingValues: List<Long>)
}
