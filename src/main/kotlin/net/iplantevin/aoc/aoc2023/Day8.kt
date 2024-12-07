package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.lcm

private typealias Nodes = Map<String, Pair<String, String>>

object Day8 {

    private val nodeRegex = """(\w\w\w) = \((\w\w\w), (\w\w\w)\)""".toRegex()

    fun part1(input: String): Long {
        val rightLeftSequence = input.lines().first().toList()
        val nodes = input.lines().drop(2).parseNodes()
        var currentNode = "AAA"
        var steps = 0L
        while (true) {
            rightLeftSequence.forEach {
                currentNode = if (it == 'L') {
                    nodes[currentNode]!!.first
                } else {
                    nodes[currentNode]!!.second
                }
                steps++
                if (currentNode == "ZZZ") return steps
            }
        }
    }

    fun part2(input: String): Long {
        val rightLeftSequence = input.lines().first().toList()
        val nodes = input.lines().drop(2).parseNodes()
        val startNodes = nodes.keys.filter { it.last() == 'A' }
        val periods = startNodes.map { findCyclePeriod(nodes, it, rightLeftSequence) }
        val lcm = periods.reduce { p1, p2 -> lcm(p1, p2) }
        return lcm
    }

    private fun findCyclePeriod(nodes: Nodes, start: String, rightLeftSequence: List<Char>): Long {
        var steps = 0L
        var currentNode = start
        while (true) {
            for (direction in rightLeftSequence) {
                steps++
                val nextNode = if (direction == 'L') {
                    nodes[currentNode]!!.first
                } else {
                    nodes[currentNode]!!.second
                }
                if (nextNode.last() == 'Z') {
                    return steps
                } else {
                    currentNode = nextNode
                }
            }
        }
    }

    private fun List<String>.parseNodes(): Nodes {
        return associate { line ->
            val (_, id, left, right) = nodeRegex.matchEntire(line)!!.groupValues
            id to Pair(left, right)
        }
    }
}
