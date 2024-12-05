package net.iplantevin.aoc.aoc2024

private typealias Rules = Map<Int, List<Int>>

object Day5 {

    fun problem5a(input: String): Int {
        val (rules, updates) = parseRulesAndUpdates(input)
        var sumOfMiddlePages = 0
        updates.forEach { update ->
            val isCorrect = updateIsOrderedCorrectly(update, rules)
            val middlePageNumber = update[update.size / 2]
            if (isCorrect) sumOfMiddlePages += middlePageNumber
        }
        return sumOfMiddlePages
    }

    fun problem5b(input: String): Int {
        val (rules, updates) = parseRulesAndUpdates(input)
        var sumOfMiddlePages = 0
        updates.forEach { update ->
            val isCorrect = updateIsOrderedCorrectly(update, rules)
            if (!isCorrect) {
                val sortedUpdate = sortUpdate(update, rules)
//                println("$update  ==>  $sortedUpdate")
                val middlePageNumber = sortedUpdate[update.size / 2]
                sumOfMiddlePages += middlePageNumber
            }
        }
        return sumOfMiddlePages
    }

    private fun parseRulesAndUpdates(input: String): Pair<Rules, List<List<Int>>> {
        val (rawRules, rawUpdates) = input.split("\n\n")
        val rules = rawRules.lines().map { rule ->
            val (left, right) = rule.split("|")
            left.toInt() to right.toInt()
        }.groupBy({ it.second }) { it.first }

        val updates = rawUpdates.lines().map { line ->
            line.split(",").map { it.toInt() }
        }
        return Pair(rules, updates)
    }

    private fun updateIsOrderedCorrectly(update: List<Int>, rules: Rules): Boolean {
        val pagesThatShouldHaveBeenListedAlready = mutableSetOf<Int>()
        update.forEach { pageNumber ->
            if (pageNumber in pagesThatShouldHaveBeenListedAlready) return false

            pagesThatShouldHaveBeenListedAlready += rules[pageNumber].orEmpty()
        }
        return true
    }

    private fun sortUpdate(update: List<Int>, rules: Rules): List<Int> {
        val pagesThatShouldHaveBeenListedAlready = mutableListOf<Pair<Int, Int>>()
        val sortedUpdate = mutableListOf<Int>()
        update.forEach { pageNumber ->
            val shouldBeBefore = pagesThatShouldHaveBeenListedAlready.firstOrNull {
                it.first == pageNumber && it.second in sortedUpdate
            }?.second
            if (shouldBeBefore != null) {
                val index = sortedUpdate.indexOf(shouldBeBefore)
                sortedUpdate.add(index, pageNumber)
            } else {
                sortedUpdate += pageNumber
            }
            pagesThatShouldHaveBeenListedAlready += rules[pageNumber].orEmpty().map { it to pageNumber }
        }
        return if (sortedUpdate == update) update else sortUpdate(sortedUpdate, rules)
    }
}
