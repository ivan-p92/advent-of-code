package net.iplantevin.aoc.aoc2023

import java.util.LinkedList
import kotlin.math.pow

object Day4 {
    private val cardRegex = """Card\s+(\d+):(.*)\|(.*)""".toRegex()

    fun problem4a(input: String): Int {
        val cards = input.lines().map { it.toCard() }
        return cards.sumOf { it.points }
    }

    fun problem4b(input: String): Int {
        val cards = input.lines().map { it.toCard() }
        val queue = LinkedList(cards)
        var totalScratchCards = cards.size
        while (queue.isNotEmpty()) {
            val card = queue.pop()
            totalScratchCards += card.totalWinningNumbers
            repeat(card.totalWinningNumbers) { nextCardOffset ->
                queue.offer(cards[card.id + nextCardOffset])
            }
        }
        return totalScratchCards
    }

    private data class Card(
        val id: Int,
        val winningNumbers: Set<Int>,
        val numbers: List<Int>,
    ) {
        val totalWinningNumbers = numbers.count { it in winningNumbers }
        val points = 2.0.pow(totalWinningNumbers.toDouble() - 1).toInt()
    }

    private fun String.toCard(): Card {
        val matchResult = cardRegex.matchEntire(this)!!
        val id = matchResult.groups[1]!!.value.toInt()
        val winningNumbers = matchResult.groups[2]!!.value
            .split(" ").mapNotNullTo(mutableSetOf()) { it.toIntOrNull() }
        val numbers = matchResult.groups[3]!!.value
            .split(" ").mapNotNull { it.toIntOrNull() }
        return Card(id, winningNumbers, numbers)
    }
}
