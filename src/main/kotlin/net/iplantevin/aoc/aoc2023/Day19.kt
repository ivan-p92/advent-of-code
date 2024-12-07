package net.iplantevin.aoc.aoc2023

object Day19 {

    fun part1(input: String): Long {
        val (workflowsInput, partsInput) = input.split("\n\n")
        val workflows = parseWorkflows(workflowsInput)
        val parts = parseParts(partsInput)
        val acceptedParts = parts.filter { part -> sortPart(part, workflows) == "A" }
        return acceptedParts.sumOf { it.specs.values.sum() }
    }

    private fun parseWorkflows(workflowsInput: String): Map<String, Workflow> =
        workflowsInput.lines().map { Workflow(it) }.associateBy { it.name }

    private fun parseParts(partsInput: String): List<Part> = partsInput.lines().map { Part(it) }

    private fun sortPart(part: Part, workflows: Map<String, Workflow>): String {
        var destination = "in"
        do {
            destination = workflows[destination]!!.evaluate(part)
        } while (destination != "A" && destination != "R")
        return destination
    }

    private class Workflow(config: String) {
        private val workflowRegex = """(\w+)\{(.*)}""".toRegex()
        private val ruleRegex = """(\w)([><])(\d+):(\w+)""".toRegex()
        private val rules: List<Rule>

        val name: String

        init {
            val (_, name, rawRules) = workflowRegex.matchEntire(config)!!.groupValues
            this.name = name
            rules = rawRules.split(",").map { rule ->
                val match = ruleRegex.matchEntire(rule)
                if (match != null) {
                    val (_, category, operation, valueString, destination) = match.groupValues
                    val value = valueString.toLong()
                    Rule(
                        { part ->
                            if (operation == "<") {
                                part.specs[category]!! < value
                            } else {
                                part.specs[category]!! > value
                            }
                        },
                        destination,
                    )
                } else {
                    Rule({ true }, rule)
                }
            }
        }

        fun evaluate(part: Part): String = rules.first { it.matches(part) }.destination

        private class Rule(private val condition: (Part) -> Boolean, val destination: String) {

            fun matches(part: Part): Boolean = condition(part)
        }
    }

    private class Part(config: String) {
        val specs: Map<String, Long> = config.substring(1 until config.length - 1)
            .split(",")
            .associate { spec ->
                val (category, rating) = spec.split("=")
                category to rating.toLong()
            }
    }
}
