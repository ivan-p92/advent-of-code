package net.iplantevin.aoc.aoc2025

import net.iplantevin.aoc.common.shortestPath

object Day10 {

    fun part1(input: String): Int = parse(input).sumOf { machine ->
        val start = List(machine.targetLights.size) { false }
        shortestPath(
            start,
            end = { it == machine.targetLights },
            { currentLights -> machine.buttons.map { button -> currentLights.applyButton(button) } }
        ).cost
    }

    fun part2(input: String): Int {
        TODO()
    }

    private fun parse(input: String): List<Machine> {
        val machineRegex = """\[(.*)] (\(.*\)) \{(.*)}""".toRegex()
        return input.lines().map { machine ->
            val (rawLights, rawButtons, rawJoltage) = machineRegex.matchEntire(machine)!!.groupValues.drop(1)
            val targetLights = rawLights.map { it != '.' }
            val buttons = rawButtons.replace("(", "").replace(")", "").split(" ").map { button ->
                button.split(",").map { it.toInt() }
            }
            val joltage = rawJoltage.split(",").map { it.toInt() }
            Machine(targetLights, buttons, joltage)
        }
    }

    private data class Machine(val targetLights: List<Boolean>, val buttons: List<List<Int>>, val joltage: List<Int>)

    private fun List<Boolean>.applyButton(button: List<Int>): List<Boolean> {
        val newConfiguration = ArrayList(this)
        button.forEach {
            newConfiguration[it] = newConfiguration[it].not()
        }
        return newConfiguration
    }
}
