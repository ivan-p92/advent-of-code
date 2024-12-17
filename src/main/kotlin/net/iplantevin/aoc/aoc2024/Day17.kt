package net.iplantevin.aoc.aoc2024

import kotlin.math.pow

object Day17 {

    fun part1(input: String): String = IntCode.forInput(input).execute()!!

    fun part2(input: String): Long = TODO()

    private class IntCode(
        var a: Long = 0L,
        var b: Long = 0L,
        var c: Long = 0L,
        val program: List<Int>
    ) {
        private var pointer = 0
        private val output: MutableList<Int> = mutableListOf()

        fun printRegisters() {
            println("A: $a\nB: $b\nC: $c\n")
        }

        fun execute(copySelf: Boolean = false): String? {
            while (pointer < program.size - 1) {
                val instruction = program[pointer]
                val operand = program[pointer + 1].toLong()

                when (instruction) {
                    0 -> a = (a / (2.toDouble().pow(combo(operand).toInt()))).toLong()
                    1 -> b = b xor operand
                    2 -> b = combo(operand) % 8
                    3 -> if (a != 0L) pointer = operand.toInt() - 2
                    4 -> b = b xor c
                    5 -> output += (combo(operand) % 8).toInt()
                    6 -> b = (a / (2.toDouble().pow(combo(operand).toInt()))).toLong()
                    7 -> c = (a / (2.toDouble().pow(combo(operand).toInt()))).toLong()
                }
                if (copySelf && output.isNotEmpty() && (output.size > program.size || output.last() != program[output.size - 1])) {
                    return null
                }
                pointer += 2
            }
            if (copySelf && output != program) {
                return null
            }
            return output.joinToString(",")
        }

        private fun combo(operand: Long): Long {
            return when (operand.toInt()) {
                0, 1, 2, 3 -> operand
                4 -> a
                5 -> b
                6 -> c
                else -> throw IllegalStateException("Invalid operand: $operand")
            }
        }

        companion object {
            fun forInput(input: String): IntCode {
                val (registers, rawProgram) = input.split("\n\n")
                val (a, b, c) = registers.split("\n").map { register ->
                    register.split(" ").last().toLong()
                }
                val program = rawProgram.split(" ")[1].split(",").map { it.toInt() }
                return IntCode(a, b, c, program)
            }
        }
    }
}
