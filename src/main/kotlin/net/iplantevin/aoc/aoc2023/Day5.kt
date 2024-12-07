package net.iplantevin.aoc.aoc2023

import java.util.*
import kotlin.math.max
import kotlin.math.min

object Day5 {

    fun part1(input: String): Long {
        val almanac = loadAlmanac(input)
        return almanac.seeds.minOf { seed ->
            almanac.mappings.fold(seed) { sourceNumber, mappings ->
                mappings.firstOrNull {
                    it.mappedDestinationOrNull(sourceNumber) != null
                }?.mappedDestinationOrNull(sourceNumber) ?: sourceNumber
            }
        }
    }

    // Wew.. this one was error-prone, and took (too) long with a bruteforce approach.
    // The solution is to map each range to one or more other ranges, then picking the lowest
    // start value of the final ranges.
    fun part2(input: String): Long {
        val almanac = loadAlmanac(input)
        val seedRanges = almanac.seeds.windowed(size = 2, step = 2).map { (start, length) ->
            LongRange(start, start + length - 1)
        }
        val locationRanges = almanac.mappings.fold(seedRanges) { ranges, mappings ->
            mapRanges(ranges, mappings)
        }
        return locationRanges.minOf { it.first }
    }

    private fun mapRanges(ranges: List<LongRange>, mappings: List<Mapping>): List<LongRange> {
        val queue = LinkedList(ranges)
        val mappedRanges = mutableListOf<LongRange>()
        while (queue.isNotEmpty()) {
            val range = queue.pop()
            var intersect: Mapping.Intersect? = null
            for (mapping in mappings) {
                // A tiny optimization, since the mappings have been sorted by first value
                if (mapping.afterRange(range)) break

                // As soon as we find an intersect, we can stop going through the mappings
                intersect = mapping.intersectOrNull(range)
                if (intersect != null) break
            }
            if (intersect == null) {
                // The range fully maps to the original source values
                mappedRanges += range
            } else {
                // The intersect part of the intersect has already been mapped to destination values
                mappedRanges += intersect.mappedIntersect
                val (head, _, tail) = intersect
                // The head of the intersect falls outside any mapping, so can be added to the mapped ranges
                if (head != null) mappedRanges += head
                // The tail may (partly) intersect with mappings, so needs to be evaluated
                if (tail != null) queue.addLast(tail)
            }
        }
        return mappedRanges
    }

    private fun loadAlmanac(input: String): Almanac {
        val seeds = input.lineSequence().first().substringAfter("seeds: ").split(" ").map { it.toLong() }
        val seedToSoil = input.substringAfter("seed-to-soil map:\n").substringBefore("\n\nsoil-to-fertilizer map:")
        val soilToFertilizer =
            input.substringAfter("soil-to-fertilizer map:\n").substringBefore("\n\nfertilizer-to-water map:")
        val fertilizerToWater =
            input.substringAfter("fertilizer-to-water map:\n").substringBefore("\n\nwater-to-light map:")
        val waterToLight =
            input.substringAfter("water-to-light map:\n").substringBefore("\n\nlight-to-temperature map:")
        val lightToTemperature =
            input.substringAfter("light-to-temperature map:\n").substringBefore("\n\ntemperature-to-humidity map:")
        val temperatureToHumidity =
            input.substringAfter("temperature-to-humidity map:\n").substringBefore("\n\nhumidity-to-location map:")
        val humidityToLocation = input.substringAfter("humidity-to-location map:\n")
        return Almanac(
            seeds, listOf(
                seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature,
                temperatureToHumidity, humidityToLocation
            ).map { it.toMappings() }
        )
    }

    private fun String.toMappings(): List<Mapping> {
        return lines().map {
            val (dest, source, length) = it.split(" ").map { it.toLong() }
            Mapping(LongRange(source, source + length - 1), dest - source)
        }.sortedBy { it.sourceRange.first }
    }

    data class Almanac(val seeds: List<Long>, val mappings: List<List<Mapping>>)

    data class Mapping(val sourceRange: LongRange, val destinationOffset: Long) {
        data class Intersect(val head: LongRange?, val mappedIntersect: LongRange, val tail: LongRange?)

        fun mappedDestinationOrNull(source: Long): Long? {
            return if (source in sourceRange) {
                source + destinationOffset
            } else null
        }

        private fun beforeRange(range: LongRange): Boolean = range.first > sourceRange.last
        fun afterRange(range: LongRange): Boolean = sourceRange.first > range.last

        fun intersectOrNull(range: LongRange): Intersect? {
            return if (beforeRange(range) || afterRange(range)) {
                // There is no intersect
                return null
            } else {
                // This is the unmapped intersect, i.e. in terms of source values
                val intersect = LongRange(
                    max(range.first, sourceRange.first),
                    min(range.last, sourceRange.last)
                )
                // Any surplus before the intersect
                val head = if (intersect.first > range.first) {
                    LongRange(range.first, intersect.first - 1)
                } else null
                // Any surplus after the intersect
                val tail = if (intersect.last < range.last) {
                    LongRange(intersect.last + 1, range.last)
                } else null
                // We map the intersect to the destination values
                val mappedIntersect = LongRange(intersect.first + destinationOffset, intersect.last + destinationOffset)
                Intersect(head, mappedIntersect, tail)
            }
        }
    }
}
