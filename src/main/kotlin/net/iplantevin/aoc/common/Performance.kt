package net.iplantevin.aoc.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * Parallel mapper using coroutines.
 */
fun <A, B> Collection<A>.mapParallel(f: suspend (A) -> B): List<B> = runBlocking {
    map { async(Dispatchers.Default) { f(it) } }.awaitAll()
}
