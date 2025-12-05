package net.iplantevin.aoc.common

import kotlin.math.max
import kotlin.math.min

fun IntRange.size(): Int = last - first + 1

fun LongRange.size(): Long = last - first + 1

fun LongRange.mergeWithOrNull(other: LongRange): LongRange? =
    if (first in other || other.first in this) {
        LongRange(min(first, other.first), max(last, other.last))
    } else {
        null
    }

