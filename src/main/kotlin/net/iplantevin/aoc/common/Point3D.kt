package net.iplantevin.aoc.common

data class Point3D(val x: Long, val y: Long, val z: Long) {

    infix fun distanceTo(other: Point3D): Long {
        return (x - other.x).pow(2) + (y - other.y).pow(2) + (z - other.z).pow(2)
    }
}
