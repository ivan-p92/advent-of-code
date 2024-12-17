package net.iplantevin.aoc.aoc2024

object Day17 {

    fun part1(input: String): String = IntCode.forInput(input).execute()!!

    fun part2(input: String): Long = findValidStartValue(IntCode.forInput(input).program).first()

    private fun findValidStartValue(desiredOutput: List<Int>): List<Long> {
        return if (desiredOutput.size == 1) {
            // Working backwards from the last desired output value, find the first 3-bit number to result
            // in the desired output value. We only need to consider 0-7 since the first step of the program
            // is a modulo 8.
            (0L..7L).filter { executeProgramOnce(it) == desiredOutput.first() }
        } else {
            // Recursively go through the tail first, so that we work backwards.
            findValidStartValue(desiredOutput.drop(1)).flatMap { nextValidStartValue ->
                // Since the last step of the program is to divide A by 8, to find the smallest value of A
                // that results in the current desired output, and the modulo, we need to consider all 3-bit integer
                // values again, added to the next output value's valid start value.
                (nextValidStartValue * 8..nextValidStartValue * 8 + 7).filter {
                    executeProgramOnce(it) == desiredOutput.first()
                }
            }
        }
    }

    // A hardcoded and simplified implementation of the program.
    private fun executeProgramOnce(a: Long): Int {
        var b = (a % 8) xor 2
        val c = a shr b.toInt()
        b = (b xor 3) xor c
        return (b % 8).toInt()
        // The program continues to divide A by 2^3 = 8, which we take into account above.
    }

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
                    0 -> a = a shr combo(operand).toInt()
                    1 -> b = b xor operand
                    2 -> b = combo(operand) % 8
                    3 -> if (a != 0L) pointer = operand.toInt() - 2
                    4 -> b = b xor c
                    5 -> output += (combo(operand) % 8).toInt()
                    6 -> b = a shr combo(operand).toInt()
                    7 -> c = a shr combo(operand).toInt()
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
