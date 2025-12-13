package net.iplantevin.aoc.aoc2025

object Day11 {

    fun part1(input: String): Long = numberOfPaths(parse(input), "you", "out")

    fun part2(input: String): Long {
        val wiring = parse(input)
        val fftToDac = numberOfPaths(wiring, "fft", "dac")
        val svrToFft = numberOfPaths(wiring, "svr", "fft")
        val dacToOut = numberOfPaths(wiring, "dac", "out")
        return fftToDac * svrToFft * dacToOut
    }

    private fun parse(input: String): Map<String, List<String>> = input.lines().associate { line ->
        val (inputDevice, outputDevices) = line.split(": ")

        inputDevice to outputDevices.split(" ")
    }

    private fun numberOfPaths(
        wiring: Map<String, List<String>>,
        start: String,
        end: String,
        pathCounts: MutableMap<String, Long> = mutableMapOf()
    ): Long = pathCounts.getOrPut(start) {
        if (start == end) {
            1L
        } else {
            wiring[start]?.sumOf { next -> numberOfPaths(wiring, next, end, pathCounts) } ?: 0
        }
    }
}
