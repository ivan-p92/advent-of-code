package net.iplantevin.aoc.common

import java.util.*

/**
 * Implements A* search to find the shortest path between two vertices.
 * Credits go to Mistborn94: https://github.com/Mistborn94/advent-of-code-2024
 */
fun <T> shortestPath(
    start: T,
    end: (T) -> Boolean,
    neighbours: (T) -> Iterable<T>,
    cost: (T, T) -> Int = { _, _ -> 1 },
    heuristic: (T) -> Int = { 0 }
): PathResult<T> =
    shortestPath(listOf(ScoredVertex(start, 0, heuristic(start))), end, neighbours, cost, heuristic)


private fun <T> shortestPath(
    initialToVisit: List<ScoredVertex<T>>,
    end: (T) -> Boolean,
    neighbours: (T) -> Iterable<T>,
    cost: (T, T) -> Int,
    heuristic: (T) -> Int = { 0 },
): PathResult<T> {
    val toVisit = PriorityQueue(initialToVisit)
    val seenPoints: MutableMap<T, SeenVertex<T>> =
        initialToVisit.associateTo(mutableMapOf()) { (vertex, score) -> vertex to SeenVertex(score, null) }
    val possiblePaths = mutableMapOf<T, MutableSet<T>>()
    var endVertex: T? = null

    while (endVertex == null && toVisit.isNotEmpty()) {
        val (currentVertex, currentCost) = toVisit.remove()
        endVertex = if (end(currentVertex)) currentVertex else null

        neighbours(currentVertex).forEach { next ->
            val nextCost = currentCost + cost(currentVertex, next)
            val heuristicCost = heuristic(next)
            val bestCost = seenPoints[next]?.cost ?: Int.MAX_VALUE

            if (nextCost < bestCost) {
                possiblePaths[next] = mutableSetOf(currentVertex)
                seenPoints[next] = SeenVertex(nextCost, currentVertex)
                toVisit.add(ScoredVertex(next, nextCost, heuristicCost))
            } else if (nextCost == bestCost) {
                possiblePaths[next]!!.add(currentVertex)
            }
        }
    }
    val starts = initialToVisit.mapTo(mutableSetOf()) { it.vertex }
    return PathResult(starts, endVertex, seenPoints, possiblePaths)
}

class PathResult<T>(
    val start: Set<T>,
    val end: T?,
    // Maps vertices to costs and previous vertices
    private val allVertices: Map<T, SeenVertex<T>>,
    // Maps vertex to all possible previous vertices that still results in a shortest path
    val possiblePaths: Map<T, Set<T>> = emptyMap()
) {
    val cost: Int
        get() = end?.let { getCost(it) } ?: -1

    fun getCost(vertex: T): Int =
        allVertices[vertex]?.cost ?: throw IllegalStateException("Result for $vertex not available")

    fun getVertices(): List<T> = end?.let { getVertices(it, emptyList()) } ?: throw IllegalStateException("No path found")

    fun getVertices(end: T): List<T> = getVertices(end, emptyList())

    fun getVertexInPath(end: T, startCondition: (T) -> Boolean) =
        getPathItem(end, startCondition) ?: throw IllegalStateException("No path found")

    fun seen(): Set<T> = allVertices.keys

    private tailrec fun getVertices(endVertex: T, pathEnd: List<T>): List<T> {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            listOf(endVertex) + pathEnd
        } else {
            getVertices(previous, listOf(endVertex) + pathEnd)
        }
    }

    tailrec fun getStart(endVertex: T): T {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            endVertex
        } else {
            getStart(previous)
        }
    }

    private tailrec fun getPathItem(endVertex: T, startCondition: (T) -> Boolean = { it == null }): T? {
        val previous = allVertices[endVertex]?.prev

        return if (previous == null) {
            null
        } else if (startCondition(previous)) {
            previous
        } else {
            getPathItem(previous, startCondition)
        }
    }
}

data class SeenVertex<T>(val cost: Int, val prev: T?)

data class ScoredVertex<T>(val vertex: T, val score: Int, val heuristic: Int) : Comparable<ScoredVertex<T>> {
    override fun compareTo(other: ScoredVertex<T>): Int = (score + heuristic).compareTo(other.score + other.heuristic)
}
