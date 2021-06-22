package core

//Each movement is 0-10
class Terrain(
    val name: String,
    val description: String,
    val flat: Int = 0,
    val bumpy: Int = 0,
    val tightQuarters: Int = 0,
    val water: Int = 0,
    val sky: Int = 0,
) {
}