package core

import core.actions.Action
import data.NOTHING
import kotlin.math.min
import kotlin.math.max

//Each movement is 0-10
//Distribute 10 points amongst all movements
class Core(
    override val name: String,
    override val description: String,
    override val totalHealth: Int,
    val moveFlat: Int,
    val moveBumpy: Int,
    val moveTightQuarters: Int,
    val moveWater: Int,
    val moveSky: Int,
    override val action: Action = NOTHING
) : Part {
    override var health: Int = totalHealth

    fun getMovement(terrain: Terrain) : Int {
        val total = moveFlat * terrain.flat + moveBumpy * terrain.bumpy + moveTightQuarters*terrain.tightQuarters + moveWater * terrain.tightQuarters + moveSky * terrain.sky
        return min(100, max(0, total))
    }
}