package net.iplantevin.aoc.aoc2023

object Day5 {

    fun problem5a(input: String): Long {
        val almanac = loadAlmanac(input)
        return almanac.seeds.minOf { seed ->
            almanac.mappings.fold(seed) { sourceNumber, mappings ->
                mappings.firstOrNull {
                    it.mappedDestinationOrNull(sourceNumber) != null
                }?.mappedDestinationOrNull(sourceNumber) ?: sourceNumber
            }
        }
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
            Mapping(LongRange(source, source + length - 1), dest)
        }
    }

    data class Almanac(val seeds: List<Long>, val mappings: List<List<Mapping>>)

    data class Mapping(val sourceRange: LongRange, val destinationStart: Long) {

        fun mappedDestinationOrNull(source: Long): Long? {
            return if (source in sourceRange) {
                source + (destinationStart - sourceRange.first)
            } else null
        }
    }
}
