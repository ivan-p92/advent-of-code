package net.iplantevin.aoc.common

fun <A, B> List<A>.distinctPairs(other: List<B>): Sequence<Pair<A, B>> = sequence {
    for (i in indices)
        for (j in i + 1..<other.size)
            yield(get(i) to other[j])
}

fun <T> List<T>.distinctPairs(): Sequence<Pair<T, T>> = sequence {
    for (i in indices)
        for (j in i + 1..<size)
            yield(get(i) to get(j))
}
