package ui

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

fun fromDelta(xd: Double, yd: Double) : Direction {
    return when {
        xd > 0 -> Direction.RIGHT
        xd < 0 -> Direction.LEFT
        yd < 0 -> Direction.UP
        yd > 0 -> Direction.DOWN
        else -> Direction.DOWN
    }
}