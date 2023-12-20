package net.iplantevin.aoc.common

fun gcd(a: Long, b: Long): Long {
    var num1 = a
    var num2 = b
    while (num2 != 0L) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

fun lcm(a: Long, b: Long): Long = a * (b / gcd(a, b))
