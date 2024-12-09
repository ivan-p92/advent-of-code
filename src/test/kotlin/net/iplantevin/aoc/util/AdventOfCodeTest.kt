package net.iplantevin.aoc.util

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

abstract class AdventOfCodeTest(private val year: Int, private val warmupIterations: Int = 0) {

    fun input(day: Int): String {
        val directory = "src/test/resources/inputs/$year"
        val file = File("$directory/aoc${year}_$day.txt")

        if (!file.exists()) {
            File(directory).mkdirs()
            downloadInputFile(day, file)
        }

        return file.readText()
    }

    fun timing(warmupIterations: Int = this.warmupIterations, averageOverIterations: Boolean = false, f: () -> Unit) {
        var start = if (averageOverIterations) System.nanoTime() else null
        repeat(warmupIterations) { f() }

        start = start ?: System.nanoTime()
        f()
        val end = System.nanoTime()
        val duration =
            if (averageOverIterations) (end.toDouble() - start) / (warmupIterations + 1) else (end.toDouble() - start)
        println("Took ${duration / 1e6} ms")
    }

    private fun downloadInputFile(day: Int, file: File) {
        val cookie = File("src/test/resources/aoc_cookie").readText()
        val request = HttpRequest.newBuilder(URI("https://adventofcode.com/$year/day/$day/input"))
            .header("Cookie", cookie)
            .header("accept", "text/plain")
            .GET()
            .build()

        val response = HttpClient.newHttpClient()
            .send(request, BodyHandlers.ofFile(file.toPath()))

        if (response.statusCode() != 200) {
            file.delete()
            throw IllegalArgumentException("Failed to download input file: Http ${response.statusCode()}")
        }
        val trimmed = file.readText().trimEnd()
        file.writeBytes(trimmed.toByteArray())
    }
}
