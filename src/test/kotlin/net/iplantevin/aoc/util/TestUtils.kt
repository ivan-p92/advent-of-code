package net.iplantevin.aoc.util

var globalWarmupIterations = 0

fun timing(warmupIterations: Int? = null, f: () -> Unit) {
    repeat(warmupIterations ?: globalWarmupIterations) { f() }

    val start = System.nanoTime()
    f()
    val end = System.nanoTime()
    println("Took ${(end - start) / 1.0e6} ms")
}

fun <R : Any> R.input(year: Int, day: Int) =
    this::class.java.classLoader.getResource("aoc${year}_$day.txt").readText()
