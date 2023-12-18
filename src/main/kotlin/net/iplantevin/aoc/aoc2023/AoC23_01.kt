package net.iplantevin.aoc.aoc2023

fun problem1a(input: String): Int {
    return input.lines().sumOf { line ->
        (line.first { it.isDigit() }.toString() + line.last { it.isDigit() }).toInt()
    }
}

fun problem1b(input: String): Int {
    return input.lines().sumOf { parseLine(it) }
}

private fun parseLine(line: String): Int {
    val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var firstDigit: Int? = null
    var lastDigit: Int? = null
    var match: Int? = null

    line.forEachIndexed { index, char ->
        if (char.isDigit()) {
            match = char.digitToInt()
        } else {
            val substring = line.substring(index)
            val textualDigitIndex = digits.indexOfFirst { substring.startsWith(it) }
            if (textualDigitIndex >= 0) {
                match = textualDigitIndex + 1
            }
        }
        when {
            match != null && firstDigit != null -> {
                lastDigit = match
            }
            match != null -> {
                firstDigit = match
            }
        }
        match = null
    }
    if (lastDigit == null) lastDigit = firstDigit

    return "$firstDigit$lastDigit".toInt()
}
