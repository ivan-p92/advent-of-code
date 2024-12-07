package net.iplantevin.aoc.aoc2023

object Day15 {

    fun part1(initSequence: String): Int {
        return initSequence.split(",").sumOf { hash(it) }
    }

    fun part2(initSequence: String): Int {
        val boxes = mutableMapOf<Int, LinkedHashMap<String, Int>>()
        repeat(256) { boxes[it] = LinkedHashMap() }
        initSequence.split(",").forEach {
            val addLens = it.split("=")
            if (addLens.size == 2) {
                val focalLength = addLens.last().toInt()
                val label = addLens.first()
                boxes[hash(label)]!![label] = focalLength
            } else {
                val label = it.substringBefore("-")
                boxes[hash(label)]!!.remove(label)
            }
        }
        var totalFocusingPower = 0
        boxes.forEach { (box, lenses) ->
            lenses.entries.forEachIndexed { index, (_, focalLength) ->
                totalFocusingPower += (box + 1) * (index + 1) * focalLength
            }
        }
        return totalFocusingPower
    }

    private fun hash(string: String): Int {
        var currentValue = 0
        string.forEach { char ->
            currentValue += char.code
            currentValue *= 17
            currentValue %= 256
        }
        return currentValue
    }
}
