package net.iplantevin.aoc.aoc2023

import net.iplantevin.aoc.common.Point

object Day3 {

    fun problem3a(schematic: String): Int {
        val (numbersByPoint, symbols) = loadSchematic(schematic)
        val partNumbers = mutableSetOf<Number>()

        symbols.forEach { symbol ->
            symbol.point.adjacentPoints().forEach { adjacentPoint ->
                val partNumber = numbersByPoint[adjacentPoint]
                if (partNumber != null) partNumbers += partNumber
            }
        }
        return partNumbers.sumOf { it.value }
    }

    fun problem3b(schematic: String): Int {
        val (numbersByPoint, symbols) = loadSchematic(schematic)
        var sumOfGearRatios = 0
        symbols.forEach { symbol ->
            if (symbol.value == '*') {
                val adjacentParts = symbol.point.adjacentPoints().mapNotNullTo(mutableSetOf()) { numbersByPoint[it] }
                if (adjacentParts.size == 2) {
                    sumOfGearRatios += adjacentParts.first().value * adjacentParts.last().value
                }
            }
        }
        return sumOfGearRatios
    }

    private fun loadSchematic(schematic: String): Pair<Map<Point, Number>, List<Symbol>> {
        var currentNumber: Number? = null
        val numbersByPoint = mutableMapOf<Point, Number>()
        val symbols = mutableListOf<Symbol>()

        schematic.lines().forEachIndexed { y: Int, row: String ->
            if (currentNumber != null) currentNumber = null
            row.forEachIndexed { x, c ->
                val point = Point(x, y)
                when {
                    c == '.' -> {
                        if (currentNumber != null) currentNumber = null
                    }

                    c.isDigit() -> {
                        if (currentNumber != null) {
                            currentNumber!!.addDigit(c)
                        } else {
                            currentNumber = Number(c.digitToInt())
                        }
                        numbersByPoint[point] = currentNumber!!
                    }

                    else -> {
                        if (currentNumber != null) currentNumber = null
                        symbols += Symbol(c, point)
                    }
                }
            }
        }
        return numbersByPoint to symbols
    }

    private class Number(var value: Int) {
        fun addDigit(digit: Char) {
            value = value * 10 + digit.digitToInt()
        }

        override fun toString(): String = "$value"
    }

    private class Symbol(val value: Char, val point: Point)
}