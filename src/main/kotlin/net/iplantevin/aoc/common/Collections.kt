package net.iplantevin.aoc.common

fun <A, B> Iterable<A>.distinctPairs(other: Iterable<B>): List<Pair<A, B>> =
    flatMapIndexed { i, a -> other.drop(i + 1).map { b -> a to b } }
