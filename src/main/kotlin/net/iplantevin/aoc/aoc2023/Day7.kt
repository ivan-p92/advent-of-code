package net.iplantevin.aoc.aoc2023

object Day7 {
    private val handRegex = """(\w\w\w\w\w) (\d+)""".toRegex()

    fun part1(input: String): Long {
        return input.parseAndSortHands().totalWinnings()
    }

    fun part2(input: String): Long {
        // We simply replace Jack cards by Joker cards by using their (arbitrarily chosen) label X
        return input.replace("J", "X").parseAndSortHands().totalWinnings()
    }

    private fun String.parseAndSortHands() = lines().map { it.parseHand() }.sorted()

    private fun String.parseHand(): Hand {
        val (_, rawCards, bid) = handRegex.matchEntire(this)!!.groupValues
        val cards = rawCards.map { Card[it] }
        return Hand(cards, cards.toType(), bid.toLong())
    }

    private fun List<Hand>.totalWinnings() = mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()

    private fun List<Card>.toType(): Hand.Type {
        val counts = groupingBy { it }.eachCount().toMutableMap()
        val highestMostOccurring = counts.filterNot { it.key == Card.JOKER }
            .maxWithOrNull(
                compareBy({ it.value }, { it.key })
            )
        // If there was no max, there are only jokers and we don't need to change anything
        if (Card.JOKER in counts && highestMostOccurring != null) {
            counts[highestMostOccurring.key] = highestMostOccurring.value + counts[Card.JOKER]!!
            counts.remove(Card.JOKER)
        }

        return when {
            counts.size == 1 -> Hand.Type.FIVE_OF_A_KIND
            counts.size == 2 && counts.containsValue(4) -> Hand.Type.FOUR_OF_A_KIND
            counts.size == 2 -> Hand.Type.FULL_HOUSE
            counts.size == 3 && counts.containsValue(3) -> Hand.Type.THREE_OF_A_KIND
            counts.values.count { it == 2 } == 2 -> Hand.Type.TWO_PAIR
            counts.containsValue(2) -> Hand.Type.ONE_PAIR
            else -> Hand.Type.HIGH_CARD
        }
    }

    private data class Hand(val cards: List<Card>, val type: Type, val bid: Long) : Comparable<Hand> {
        enum class Type {
            HIGH_CARD,
            ONE_PAIR,
            TWO_PAIR,
            THREE_OF_A_KIND,
            FULL_HOUSE,
            FOUR_OF_A_KIND,
            FIVE_OF_A_KIND,
        }

        override fun compareTo(other: Hand): Int {
            val byType = type.compareTo(other.type)
            if (byType == 0) {
                cards.forEachIndexed { index, card ->
                    val byCard = card.compareTo(other.cards[index])
                    if (byCard != 0) {
                        return byCard
                    }
                }
                return 0
            }
            return byType
        }
    }


    private enum class Card(val label: Char) {
        JOKER('X'),
        ONE('1'),
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        TEN('T'),
        JACK('J'),
        QUEEN('Q'),
        KING('K'),
        ACE('A');

        companion object {
            private val cardsByLabel = entries.associateBy { it.label }

            operator fun get(char: Char): Card = cardsByLabel[char]!!
        }
    }
}